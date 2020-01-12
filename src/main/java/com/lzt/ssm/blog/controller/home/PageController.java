package com.lzt.ssm.blog.controller.home;

import com.lzt.ssm.blog.entity.Article;
import com.lzt.ssm.blog.entity.Page;
import com.lzt.ssm.blog.enums.ArticleStatus;
import com.lzt.ssm.blog.enums.PageStatus;
import com.lzt.ssm.blog.service.ArticleService;
import com.lzt.ssm.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/12 20:13
 */
@Controller
public class PageController {

    @Autowired
    private PageService pageService;

    @Autowired
    private ArticleService articleService;

    /**
     * 根据key跳转到详情页面
     * <p>
     * 不是通过id进行跳转
     *
     * @param key
     * @param model
     * @return
     */
    @RequestMapping("/{key}")
    public String goToDetailByKey(@PathVariable("key") String key, Model model) {
        Page page = pageService.getEntityByStatusAndKey(PageStatus.NORMAL.getValue(), key);
        if (page == null) {
            return "Home/Error/404";
        }
        model.addAttribute("page", page);

        return "Home/Page/page";
    }

    /**
     * 站点地图，相关数据已在拦截器中进行初始化
     *
     * @return
     */
    @RequestMapping("/map")
    public String map(Model model) {
        //文章列表
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList", articleList);
        return "Home/Page/siteMap";
    }

    /**
     * 文章归档
     *
     * @param mv
     * @return
     */
    @RequestMapping("/articleFile")
    public ModelAndView articleFile(ModelAndView mv) {
        List<Article> articleList = articleService.listAllNotWithContent();

        mv.addObject("articleList", articleList);
        mv.setViewName("Home/Page/articleFile");
        return mv;
    }
}
