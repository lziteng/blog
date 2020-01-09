package com.lzt.ssm.blog.controller.admin;

import com.lzt.ssm.blog.entity.*;
import com.lzt.ssm.blog.enums.NoticeStatus;
import com.lzt.ssm.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author lzt
 * @date 2020/1/9 15:28
 */
@Controller
@RequestMapping("/admin/notice")
public class BackNoticeController {
    @Autowired
    private NoticeService noticeService;

    public static final String UP = "up";

    public static final String DOWN = "down";

    /**
     * 公告列表
     *
     * @param mv
     * @return
     */
    @RequestMapping("")
    public ModelAndView index(ModelAndView mv) {
        List<Notice> noticeList = noticeService.listEntity(null);
        mv.addObject("noticeList", noticeList);
        mv.setViewName("Admin/Notice/index");
        return mv;
    }

    /**
     * 编辑公告页面
     *
     * @param id
     * @param mv
     * @return
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView editNoticeView(@PathVariable("id") Integer id, ModelAndView mv) {
        Notice notice = noticeService.getEntityById(id);
        mv.addObject("notice", notice);
        mv.setViewName("Admin/Notice/edit");
        return mv;
    }

    /**
     * 提交编辑
     *
     * @param notice
     * @return
     */
    @RequestMapping(value = "editSubmit", method = RequestMethod.POST)
    public String editNoticeSubmit(Notice notice) {
        notice.setNoticeUpdateTime(new Date());
        noticeService.updateEntity(notice);
        return "redirect:/admin/notice";
    }

    /**
     * 删除公告
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String deleteNotice(@PathVariable("id") Integer id) {
        noticeService.deleteEntityById(id);
        return "redirect:/admin/notice";
    }

    /**
     * 添加页面
     *
     * @return
     */
    @RequestMapping("/insert")
    public String insertNoticeView() {
        return "Admin/Notice/insert";
    }

    /**
     * 提交添加
     *
     * @param notice
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertNoticeSubmit(Notice notice) {
        notice.setNoticeCreateTime(new Date());
        notice.setNoticeUpdateTime(new Date());
        Integer maxOrder = noticeService.getMaxOrder();
        if(maxOrder == null){
            maxOrder = 0;
        }
        notice.setNoticeOrder(++maxOrder);
        notice.setNoticeStatus(NoticeStatus.NORMAL.getValue());
        noticeService.insertEntity(notice);
        return "redirect:/admin/notice";
    }

    /**
     * 移动
     *
     * @param id        主键
     * @param direction up/down
     */
    @RequestMapping("/move/{id}")
    @ResponseBody
    public String move(@PathVariable("id") Integer id, String direction) {
        Notice nowEntity = noticeService.getEntityById(id);

        Integer nowOrder = nowEntity.getNoticeOrder();
        if (UP.equals(direction)) {
            Notice preEntity = noticeService.preEntityByOrder(nowOrder);
            nowEntity.setNoticeOrder(preEntity.getNoticeOrder());
            preEntity.setNoticeOrder(nowOrder);
            noticeService.updateEntity(nowEntity);
            noticeService.updateEntity(preEntity);
        } else if (DOWN.equals(direction)) {
            Notice nextEntity = noticeService.nextEntityByOrder(nowOrder);
            nowEntity.setNoticeOrder(nextEntity.getNoticeOrder());
            nextEntity.setNoticeOrder(nowOrder);
            noticeService.updateEntity(nowEntity);
            noticeService.updateEntity(nextEntity);
        }

        return "success";
    }
}
