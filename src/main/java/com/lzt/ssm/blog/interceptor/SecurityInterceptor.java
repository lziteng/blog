package com.lzt.ssm.blog.interceptor;

import com.lzt.ssm.blog.entity.User;
import com.lzt.ssm.blog.enums.UserType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 进入后台的身份验证
 * <p>
 * 内网用户才能进入后台，其他账号登录后，跳转到403页面
 *
 * @Author: lzt
 * @Date: 2020/1/12 22:09
 */
public class SecurityInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || user.getUserType().equals(UserType.OUT.getValue())) {
            response.sendRedirect("/403");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
