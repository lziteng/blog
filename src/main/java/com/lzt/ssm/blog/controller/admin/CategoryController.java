package com.lzt.ssm.blog.controller.admin;

import com.lzt.ssm.blog.entity.Category;
import com.lzt.ssm.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/9 21:17
 */
@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("")
    public ModelAndView index(ModelAndView mv) {
        List<Category> categoryList = categoryService.listEntity();
        mv.addObject("categoryList", categoryList);

        mv.setViewName("Admin/Category/index");
        return mv;
    }

    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertCategorySubmit(Category category) {
        //默认值
        category.setCategoryOrder(1);
        categoryService.insertEntity(category);
        return "redirect:/admin/category";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editCategoryView(@PathVariable("id") Integer id, ModelAndView mv) {
        List<Category> categoryList = categoryService.listEntity();
        mv.addObject("categoryList", categoryList);

        Category category = categoryService.getEntityById(id);
        mv.addObject("category", category);
        mv.setViewName("Admin/Category/edit");
        return mv;
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editCategorySubmit(Category category) {
        categoryService.updateEntity(category);
        return "redirect:/admin/category";
    }

    @RequestMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteEntityById(id);
        return "redirect:/admin/category";
    }

}
