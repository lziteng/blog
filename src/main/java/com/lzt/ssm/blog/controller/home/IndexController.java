package com.lzt.ssm.blog.controller.home;

import com.github.pagehelper.PageInfo;
import com.lzt.ssm.blog.entity.Article;
import com.lzt.ssm.blog.entity.Notice;
import com.lzt.ssm.blog.enums.ArticleStatus;
import com.lzt.ssm.blog.enums.NoticeStatus;
import com.lzt.ssm.blog.service.ArticleService;
import com.lzt.ssm.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/12 14:56
 */
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value = {"/", "/article"})
    public ModelAndView index(@RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                              ModelAndView mv) {

        List<Notice> noticeList = noticeService.listEntity(NoticeStatus.NORMAL.getValue());
        mv.addObject("noticeList", noticeList);

        HashMap<String, Object> criteria = new HashMap<>(1);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());

        PageInfo<Article> articlePageInfo = articleService.pageEntity(pageIndex, pageSize, criteria);
        mv.addObject("pageInfo", articlePageInfo);
        mv.addObject("pageUrlPrefix", "/article?pageIndex");

        mv.setViewName("Home/index");
        return mv;
    }

    @RequestMapping("/search")
    public ModelAndView search(@RequestParam(required = true) String keywords,
                               @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                               @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                               ModelAndView mv) {

        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        criteria.put("keywords", keywords);
        PageInfo<Article> articlePageInfo = articleService.pageEntity(pageIndex, pageSize, criteria);
        mv.addObject("pageInfo", articlePageInfo);
        mv.addObject("keywords", keywords);
        mv.addObject("pageUrlPrefix", "/search?keywords=" + keywords + "&pageIndex");

        mv.setViewName("Home/Page/search");
        return mv;
    }

    @RequestMapping("/403")
    public String forbidden(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "Admin/Error/403";
    }

    @RequestMapping("/404")
    public String NotFound(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "Home/Error/404";
    }

    @RequestMapping("/500")
    public String ServerError(@RequestParam(required = false) String message, Model model) {
        model.addAttribute("message", message);
        return "Home/Error/500";
    }
}
