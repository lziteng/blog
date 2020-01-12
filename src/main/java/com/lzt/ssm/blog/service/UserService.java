package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.User;

import java.util.List;

/**
 * @author lzt
 * @date 2020/1/8 10:47
 */
public interface UserService extends BaseCrudService<User> {

    /**
     * 获得用户列表
     *
     * @return 用户列表
     */
    List<User> listEntity();

    /**
     * 根据用户名和邮箱查询用户
     *
     * @param str 用户名或Email
     * @return 用户
     */
    User getEntityByNameOrEmail(String str);

    /**
     * 根据用户名查询用户
     *
     * @param name 用户名
     * @return 用户
     */
    User getEntityByName(String name);

    /**
     * 根据邮箱查询用户
     *
     * @param email Email
     * @return 用户
     */
    User getEntityByEmail(String email);

    /**
     * 获取所有内网用户记录
     *
     * @return 所有内网用户记录
     */
    List<User> listInUser();
}