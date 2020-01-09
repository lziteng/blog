package com.lzt.ssm.blog.controller.admin;

import com.lzt.ssm.blog.entity.Tag;
import com.lzt.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/9 20:18
 */
@Controller
@RequestMapping("/admin/tag")
public class BackTagController {
    @Autowired
    private TagService tagService;

    @RequestMapping("")
    private ModelAndView index(ModelAndView mv){
        List<Tag> tagList = tagService.listEntity();
        mv.addObject("tagList",tagList);
        mv.setViewName("Admin/Tag/index");
        return mv;
    }

    @RequestMapping(value="/insertSubmit",method = RequestMethod.POST)
    public String insertTagSubmit(Tag tag){
        tagService.insertEntity(tag);
        return "redirect:/admin/tag";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editTagView(@PathVariable("id") Integer id,ModelAndView mv){
        List<Tag> tagList = tagService.listEntity();
        mv.addObject("tagList",tagList);

        Tag tag = tagService.getEntityById(id);
        mv.addObject("tag",tag);

        mv.setViewName("Admin/Tag/edit");
        return mv;
    }

    @RequestMapping(value="/editSubmit",method = RequestMethod.POST)
    public String editTagSubmit(Tag tag){
        tagService.updateEntity(tag);
        return "redirect:/admin/tag";
    }

    @RequestMapping("/delete/{id}")
    public String deleteTag(@PathVariable("id") Integer id){
        tagService.deleteEntityById(id);
        return "redirect:/admin/tag";
    }
}
