package com.lzt.ssm.blog.controller.home;

import com.github.pagehelper.PageInfo;
import com.lzt.ssm.blog.entity.*;
import com.lzt.ssm.blog.enums.ArticleStatus;
import com.lzt.ssm.blog.exception.ReturnViewException;
import com.lzt.ssm.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @Author: lzt
 * @Date: 2020/1/12 19:30
 */
@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/category/{categoryId}")
    public String getArticleListByCategory(@PathVariable("categoryId") Integer categoryId,
            @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize, Model model) throws Exception {
        Category category = categoryService.getEntityById(categoryId);
        if (category == null) {
            throw new ReturnViewException("获取分类异常");
        }
        model.addAttribute("category", category);

        HashMap<String, Object> criteria = new HashMap<>(2);
        criteria.put("categoryId", categoryId);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());

        PageInfo<Article> pageInfo = articleService.pageEntity(pageIndex, pageSize, criteria);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("pageUrlPrefix", "/category/" + categoryId + "?pageIndex");

        return "Home/Page/articleListByCategory";
    }
}
