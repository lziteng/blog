package com.lzt.ssm.blog.controller.admin;

import com.lzt.ssm.blog.entity.Page;
import com.lzt.ssm.blog.exception.ReturnViewException;
import com.lzt.ssm.blog.service.PageService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: lzt
 * @Date: 2020/1/11 15:14
 */
@Controller
@RequestMapping("/admin/page")
public class BackPageController {

    @Autowired
    private PageService pageService;

    @RequestMapping("")
    public ModelAndView index(ModelAndView mv) {
        List<Page> pageList = pageService.listEntity();
        mv.addObject("pageList", pageList);

        mv.setViewName("Admin/Page/index");
        return mv;
    }

    @RequestMapping("/insert")
    public String insertView() {
        return "Admin/Page/insert";
    }

    /**
     * 检测别名是否存在
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkKey", method = RequestMethod.POST)
    @ResponseBody
    public String checkKey(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>(2);
        String key = request.getParameter("key");
        int pageId = Integer.valueOf(request.getParameter("id"));
        Page page = pageService.getEntityByKey(key);
        if (page != null) {
            if (page.getPageId() != pageId) {
                map.put("code", 1);
                map.put("msg", "别名已存在,请重新输入!");
            } else {
                map.put("code", 0);
                map.put("msg", "");
            }
        } else {
            map.put("code", 0);
            map.put("msg", "");
        }

        String result = new JSONObject(map).toString();
        return result;
    }

    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertSubmit(Page page) throws Exception {
        page.setPageCreateTime(new Date());
        page.setPageUpdateTime(new Date());
        page.setPageViewCount(0);
        page.setPageCommentCount(0);
        pageService.insertEntity(page);

        return "redirect:/admin/page";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editView(@PathVariable("id") Integer id, ModelAndView mv) throws Exception {
        Page page = pageService.getEntityById(id);
        if (page == null) {
            throw new ReturnViewException("页面不存在");
        }
        mv.addObject("page", page);

        mv.setViewName("Admin/Page/edit");
        return mv;
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(Page page) throws Exception {
        page.setPageUpdateTime(new Date());
        pageService.updateEntity(page);
        return "redirect:/admin/page";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) throws Exception {
        pageService.deleteEntityById(id);
        return "redirect:/admin/page";
    }
}
