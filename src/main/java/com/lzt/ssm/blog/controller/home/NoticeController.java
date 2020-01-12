package com.lzt.ssm.blog.controller.home;

import com.lzt.ssm.blog.entity.Notice;
import com.lzt.ssm.blog.enums.NoticeStatus;
import com.lzt.ssm.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: lzt
 * @Date: 2020/1/12 21:02
 */
@Controller
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @RequestMapping("/notice/{noticeId}")
    public String goToDetail(@PathVariable("noticeId") Integer noticeId, Model model) {
        Notice notice = noticeService.getEntityByStatusAndId(NoticeStatus.NORMAL.getValue(), noticeId);
        if (notice == null) {
            return "Home/Error/404";
        }
        model.addAttribute("notice", notice);

        return "Home/Page/noticeDetail";
    }
}
