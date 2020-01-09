package com.lzt.ssm.blog.controller.admin;

import com.lzt.ssm.blog.entity.Link;
import com.lzt.ssm.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * @author lzt
 * @date 2020/1/9 11:51
 */
@Controller
@RequestMapping("/admin/link")
public class BackLinkController {

    @Autowired
    private LinkService linkService;

    public static final String UP = "up";

    public static final String DOWN = "down";

    /**
     * 列表页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("")
    public ModelAndView index(ModelAndView mv) {
        List<Link> linkList = linkService.listEntity(null);
        mv.addObject("linkList", linkList);
        mv.setViewName("Admin/Link/index");
        return mv;
    }

    /**
     * 编辑页面
     *
     * @param id
     * @param mv
     * @return
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView editLinkView(@PathVariable("id") Integer id, ModelAndView mv) {
        Link link = linkService.getEntityById(id);

        mv.addObject("linkCustom", link);
        mv.setViewName("Admin/Link/edit");

        List<Link> linkList = linkService.listEntity(null);
        mv.addObject("linkList", linkList);
        return mv;
    }

    /**
     * 提交编辑
     *
     * @param link
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editLinkSubmit(Link link) {
        link.setLinkUpdateTime(new Date());
        linkService.updateEntity(link);
        return "redirect:/admin/link";
    }

    /**
     * 添加页面
     *
     * @param mv
     * @return
     */
    @RequestMapping("/insert")
    public ModelAndView insertLinkView(ModelAndView mv) {
        List<Link> linkList = linkService.listEntity(null);
        mv.addObject("linkList", linkList);

        mv.setViewName("Admin/Link/insert");
        return mv;
    }

    /**
     * 提交添加
     *
     * @param link
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertLinkSubmit(Link link) {
        link.setLinkUpdateTime(new Date());
        link.setLinkCreateTime(new Date());
        Integer linkOrder = linkService.getMaxOrder();
        if (linkOrder == null) {
            linkOrder = 0;
        }
        link.setLinkOrder(++linkOrder);
        linkService.insertEntity(link);
        return "redirect:/admin/link/insert";
    }

    /**
     * 删除链接
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String deleteLink(@PathVariable("id") Integer id) {
        linkService.deleteEntityById(id);
        return "redirect:/admin/link";
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
        Link nowEntity = linkService.getEntityById(id);
        Integer nowOrder = nowEntity.getLinkOrder();
        if (UP.equals(direction)) {
            Link preEntity = linkService.preEntityByOrder(nowOrder);
            nowEntity.setLinkOrder(preEntity.getLinkOrder());
            preEntity.setLinkOrder(nowOrder);
            linkService.updateEntity(nowEntity);
            linkService.updateEntity(preEntity);
        } else if (DOWN.equals(direction)) {
            Link nextEntity = linkService.nextEntityByOrder(nowOrder);
            nowEntity.setLinkOrder(nextEntity.getLinkOrder());
            nextEntity.setLinkOrder(nowOrder);
            linkService.updateEntity(nowEntity);
            linkService.updateEntity(nextEntity);
        }

        return "success";
    }
}
