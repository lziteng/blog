package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.User;

import java.util.List;

/**
 * @author lzt
 * @date 2020/1/8 10:47
 */
public interface UserService {

    /**
     * 获得用户列表
     *
     * @return 用户列表
     */
    List<User> listEntity();

    /**
     * 根据id查询用户信息
     *
     * @param id 用户ID
     * @return 用户
     */
    User getEntityById(Integer id);

    /**
     * 修改用户信息
     *
     * @param user 用户
     */
    void updateEntity(User user);

    /**
     * 删除用户
     *
     * @param id 用户ID
     */
    void deleteEntityById(Integer id);

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 用户
     */
    User insertEntity(User user);

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
}