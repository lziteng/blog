package com.lzt.ssm.blog.controller.admin;

import com.lzt.ssm.blog.entity.Options;
import com.lzt.ssm.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: lzt
 * @Date: 2020/1/10 21:49
 */
@Controller
@RequestMapping("/admin/options")
public class BackOptionsController {

    @Autowired
    private OptionsService optionsService;

    @RequestMapping("")
    public ModelAndView index(ModelAndView mv) {
        Options option = optionsService.getDefaultEntity();
        mv.addObject("option", option);
        mv.setViewName("Admin/Options/index");
        return mv;
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(Options options) {
        optionsService.updateEntity(options);
        return "redirect:/admin/options";
    }
}
