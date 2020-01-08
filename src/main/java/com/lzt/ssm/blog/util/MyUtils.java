package com.lzt.ssm.blog.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lzt
 * @date 2020/1/8 16:12
 */
public class MyUtils {
    public static String getIp(HttpServletRequest request) {

        String ip = request.getHeader("X-ClientIP");

        if (ip == null || ip.length() == 0) {
            ip = request.getHeader("X-Forwarded-For");
        }

        if (ip == null || ip.length() == 0) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
