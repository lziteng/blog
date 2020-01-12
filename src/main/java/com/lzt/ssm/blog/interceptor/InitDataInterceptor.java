package com.lzt.ssm.blog.interceptor;

import com.lzt.ssm.blog.entity.*;
import com.lzt.ssm.blog.enums.ArticleStatus;
import com.lzt.ssm.blog.enums.LinkStatus;
import com.lzt.ssm.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化基础数据，用于填充页面数据
 *
 * @Author: lzt
 * @Date: 2020/1/12 15:07
 */
public class InitDataInterceptor implements HandlerInterceptor {

    @Autowired
    private MenuService menuService;

    @Autowired
    private OptionsService optionsService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private LinkService linkService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        //1、菜单数据(头部)
        List<Menu> menuList = menuService.listEntityByLevel(null);
        request.setAttribute("menuList", menuList);

        //2、系统默认配置数据
        Options options = optionsService.getDefaultEntity();
        request.setAttribute("options", options);

        //3、分类数据
        List<Category> categoryList = categoryService.listEntity();
        request.setAttribute("categoryList", categoryList);

        //4、标签数据
        List<Tag> tagList = tagService.listEntity();
        request.setAttribute("tagList", tagList);

        //5、链接数据
        List<Link> linkList = linkService.listEntity(LinkStatus.NORMAL.getValue());
        request.setAttribute("linkList", linkList);

        //6、相关统计数据
        Map<String, Object> siteBasicData = new HashMap<>(10);

        siteBasicData.put("articleCount", articleService.countEntity(ArticleStatus.PUBLISH.getValue()));
        siteBasicData.put("categoryCount", categoryList.size());
        siteBasicData.put("tagCount", tagList.size());
        siteBasicData.put("linkCount", linkList.size());
        siteBasicData.put("articleLastUpdateTime", articleService.getLastUpdateArticle().getArticleUpdateTime());
        request.setAttribute("siteBasicData", siteBasicData);

        //随机文章(用于在文章详情页、分类列表页、标签列表页展示)
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        request.setAttribute("randomArticleList", randomArticleList);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
