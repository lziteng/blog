package com.lzt.ssm.blog.controller.admin;

import com.lzt.ssm.blog.entity.User;
import com.lzt.ssm.blog.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.*;
import java.util.*;

/**
 * @author lzt
 * @date 2020/1/8 11:09
 */
@Controller
@RequestMapping("/admin/user")
public class BackUserController {

    @Autowired
    private UserService userService;

    /**
     * 后台用户列表显示
     *
     * @param mv
     * @return
     */
    @RequestMapping("")
    public ModelAndView userList(ModelAndView mv) {
        List<User> userList = userService.listUser();
        mv.addObject("userList", userList);
        mv.setViewName("Admin/User/index");
        return mv;
    }

    /**
     * 基本信息页面显示
     *
     * @param session
     * @param mv
     * @return
     */
    @RequestMapping("/profile")
    public ModelAndView userProfileView(HttpSession session, ModelAndView mv) {
        User sessionUser = (User) session.getAttribute("user");
        User user = userService.getUserById(sessionUser.getUserId());
        mv.addObject("user", user);
        mv.setViewName("Admin/User/profile");
        return mv;
    }

    /**
     * 编辑用户页面显示
     *
     * @param id 用户Id
     * @param mv
     * @return
     */
    @RequestMapping("/edit/{id}")
    public ModelAndView editUserView(@PathVariable("id") Integer id, ModelAndView mv) {
        User user = userService.getUserById(id);
        mv.addObject("user", user);
        mv.setViewName("Admin/User/edit");
        return mv;
    }

    /**
     * 编辑用户提交
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(User user) {
        userService.updateUser(user);
        return "redirect:/admin/user";
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return
     */
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/admin/user";
    }

    /**
     * 后台添加用户页面显示
     *
     * @param mv
     * @return
     */
    @RequestMapping("/insert")
    public ModelAndView insertUserView(ModelAndView mv) {
        mv.setViewName("Admin/User/insert");
        return mv;
    }

    /**
     * 后台添加用户页面提交
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/insertSubmit", method = RequestMethod.POST)
    public String insertUserSubmit(User user) {
        User userByName = userService.getUserByName(user.getUserName());
        User userByEmail = userService.getUserByEmail(user.getUserEmail());
        if (userByName == null && userByEmail == null) {
            user.setUserRegisterTime(new Date());
            user.setUserStatus(1);
            userService.insertUser(user);
        }
        return "redirect:/admin/user";
    }

    /**
     * 检查用户名是否存在
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkUserName", method = RequestMethod.POST)
    @ResponseBody
    public String checkUserName(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        User user = userService.getUserByName(username);
        int id = Integer.valueOf(request.getParameter("id"));
        if (user != null) {
            //用户名已存在,但不是当前用户(编辑用户的时候，不提示)
            if (user.getUserId() != id) {
                map.put("code", 1);
                map.put("msg", "用户名已存在!");
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

    /**
     * 检查Email是否存在
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/checkUserEmail", method = RequestMethod.POST)
    @ResponseBody
    public String checkUserEmail(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String email = request.getParameter("email");
        User user = userService.getUserByEmail(email);
        int id = Integer.valueOf(request.getParameter("id"));
        if (user != null) {
            //email已存在,但不是当前用户(编辑用户的时候，不提示)
            if (user.getUserId() != id) {
                map.put("code", 1);
                map.put("msg", "电子邮箱已存在!");
            }
        } else {
            map.put("code", 0);
            map.put("msg", "");
        }

        String result = new JSONObject(map).toString();
        return result;
    }

}
