package com.lzt.ssm.blog.controller.home;

import com.github.pagehelper.PageInfo;
import com.lzt.ssm.blog.entity.Article;
import com.lzt.ssm.blog.entity.Tag;
import com.lzt.ssm.blog.enums.ArticleStatus;
import com.lzt.ssm.blog.service.ArticleService;
import com.lzt.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

/**
 * @Author: lzt
 * @Date: 2020/1/12 19:49
 */
@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/tag/{tagId}")
    public String getArticleListByTag(@PathVariable("tagId") Integer tagId,
                                      @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                      @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                      Model model) {
        Tag tag = tagService.getEntityById(tagId);
        if (tag == null) {
            return "Home/Error/404";
        }
        model.addAttribute("tag", tag);

        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("tagId", tagId);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());

        PageInfo<Article> pageInfo = articleService.pageEntity(pageIndex, pageSize, criteria);

        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageUrlPrefix", "/tag/" + tagId + "?pageIndex");

        return "Home/Page/articleListByTag";
    }
}
