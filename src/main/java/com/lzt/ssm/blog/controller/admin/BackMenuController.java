package com.lzt.ssm.blog.controller.admin;

import com.lzt.ssm.blog.entity.Menu;
import com.lzt.ssm.blog.enums.MenuLevel;
import com.lzt.ssm.blog.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/11 11:19
 */
@Controller
@RequestMapping("/admin/menu")
public class BackMenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 上移
     */
    public static final String UP = "up";

    /**
     * 下移
     */
    public static final String DOWN = "down";

    @RequestMapping("")
    public ModelAndView index(ModelAndView mv) {
        List<Menu> topMenuList = menuService.listEntityByLevel(MenuLevel.TOP_MENU.getValue());
        List<Menu> mainMenuList = menuService.listEntityByLevel(MenuLevel.MAIN_MENU.getValue());
        mv.addObject("topMenuList", topMenuList);
        mv.addObject("mainMenuList", mainMenuList);

        mv.setViewName("Admin/Menu/index");
        return mv;
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editView(@PathVariable("id") Integer id, ModelAndView mv) {
        List<Menu> topMenuList = menuService.listEntityByLevel(MenuLevel.TOP_MENU.getValue());
        List<Menu> mainMenuList = menuService.listEntityByLevel(MenuLevel.MAIN_MENU.getValue());
        mv.addObject("topMenuList", topMenuList);
        mv.addObject("mainMenuList", mainMenuList);
        Menu menu = menuService.getEntityById(id);
        mv.addObject("menu", menu);

        mv.setViewName("Admin/Menu/edit");
        return mv;
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(Menu menu) {
        menuService.updateEntity(menu);
        return "redirect:/admin/menu";
    }

    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertSubmit(Menu menu) {
        Integer maxOrder = menuService.getMaxOrder();
        if (maxOrder == null) {
            maxOrder = 0;
        }
        menu.setMenuOrder(++maxOrder);

        menuService.insertEntity(menu);

        return "redirect:/admin/menu";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        menuService.deleteEntityById(id);
        return "redirect:/admin/menu";
    }

    /**
     * 上下移动操作
     *
     * @param id        id
     * @param direction 移动方向
     * @param level     级别
     * @return
     */
    @RequestMapping("/move/{id}/{direction}/{level}")
    @ResponseBody
    public String move(@PathVariable("id") Integer id, @PathVariable("direction") String direction, @PathVariable("level") Integer level) {
        Menu nowEntity = menuService.getEntityById(id);
        Integer nowOrder = nowEntity.getMenuOrder();
        if (UP.equals(direction)) {
            Menu preEntity = menuService.getPreEntityByOrder(level, nowOrder);
            nowEntity.setMenuOrder(preEntity.getMenuOrder());
            preEntity.setMenuOrder(nowOrder);
            menuService.updateEntity(nowEntity);
            menuService.updateEntity(preEntity);
        } else if (DOWN.equals(direction)) {
            Menu nextEntity = menuService.getNextEntityByOrder(level, nowOrder);
            nowEntity.setMenuOrder(nextEntity.getMenuOrder());
            nextEntity.setMenuOrder(nowOrder);
            menuService.updateEntity(nowEntity);
            menuService.updateEntity(nextEntity);
        }

        return "success";
    }
}
