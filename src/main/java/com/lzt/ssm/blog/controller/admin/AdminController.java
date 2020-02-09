package com.lzt.ssm.blog.controller.admin;

import com.lzt.ssm.blog.entity.*;
import com.lzt.ssm.blog.service.*;
import com.lzt.ssm.blog.util.MyUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.*;

/**
 * @author lzt
 * @date 2020/1/8 15:48
 */
@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    /**
     * 后台首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/admin")
    public String index(Model model) throws Exception {
        //最新发布
        List<Article> articleList = articleService.listRecentArticle(5);
        model.addAttribute("articleList", articleList);

        List<Comment> commentList = commentService.listRecentComment(10);
        model.addAttribute("commentList", commentList);

        return "Admin/index";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "Admin/login";
    }

    /**
     * 登录验证
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");
        User user = userService.getEntityByNameOrEmail(username);
        if (user == null) {
            map.put("code", 1);
            map.put("msg", "用户名无效");
        } else if (!user.getUserPass().equals(password)) {
            map.put("code", 1);
            map.put("msg", "密码错误!");
        } else {
            map.put("code", 0);
            map.put("msg", "");
            //控制跳转路径
            map.put("userType", user.getUserType());
            //添加session
            request.getSession().setAttribute("user", user);
            //添加cookie
            if (rememberme != null) {
                //创建Cookie对象
                Cookie nameCookie = new Cookie("username", username);
                //设置Cookie的有效期为3天
                nameCookie.setMaxAge(60 * 60 * 24 * 3);

                Cookie pwdCookie = new Cookie("password", password);
                pwdCookie.setMaxAge(60 * 60 * 24 * 3);
                response.addCookie(nameCookie);
                response.addCookie(pwdCookie);
            }
            user.setUserLastLoginTime(new Date());
            user.setUserLastLoginIp(MyUtils.getIp(request));
            userService.updateEntity(user);
        }
        String result = new JSONObject(map).toString();
        return result;
    }

    /**
     * 退出登录
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "redirect:/login";
    }
}
