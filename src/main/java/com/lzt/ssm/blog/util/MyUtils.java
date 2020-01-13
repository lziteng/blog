package com.lzt.ssm.blog.util;

import cn.hutool.crypto.SecureUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lzt
 * @date 2020/1/8 16:12
 */
public class MyUtils {

    /**
     * 根据request获取ip
     *
     * @param request request
     * @return 访问的ip
     */
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


    /**
     * 根据email获取图片链接
     * <p>
     * 相关链接:http://cn.gravatar.com/site/implement/images/
     *
     * <p>
     * 参数：
     * 尺寸:将通过使用s=或size=参数并传递单个像素尺寸（由于图像为正方形）从Gravatar动态传递;
     *
     * <p>
     * 预设图片:通过在d= or default=参数中为图片提供URL来轻松实现,
     * 内置选项：
     * 404：如果没有任何图像与电子邮件哈希无关，则不加载任何图像，而是返回HTTP 404（找不到文件）响应
     * mp：（神秘人物）一个人的简单卡通风格的轮廓（不随电子邮件哈希值而变化）
     * identicon：基于电子邮件哈希的几何图案
     * monsterid：生成的具有不同颜色，面孔等的“怪物”
     * wavatar：生成的具有不同特征和背景的面孔
     * retro：生成的令人敬畏的8位街机风格像素化面孔
     * robohash：具有不同颜色，面部等的生成的机器人
     * blank：透明的PNG图像（以下为演示目的添加到HTML的边框）
     *
     * <p>
     * 评分:
     * 使用r=或rating=参数
     * g：适合在任何受众类型的所有网站上显示。
     * pg：可能包含粗鲁的手势，挑逗的个人，较轻的脏话或轻度的暴力。
     * r：可能包含苛刻的亵渎，激烈的暴力行为，裸露或滥用毒品等内容。
     * x：可能包含顽固的性意象或极为令人不安的暴力行为。
     *
     * @param email
     * @return
     */
    public static String getAvatar(String email) {
        String emailHash = SecureUtil.md5(email);
        //设置图片大小32px
        String avatar = "http://cn.gravatar.com/avatar/" + emailHash + "?s=128&d=identicon&r=PG";
        return avatar;
    }
}
