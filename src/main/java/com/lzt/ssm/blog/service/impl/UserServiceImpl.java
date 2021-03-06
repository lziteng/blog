package com.lzt.ssm.blog.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.lzt.ssm.blog.entity.*;
import com.lzt.ssm.blog.enums.*;
import com.lzt.ssm.blog.mapper.UserMapper;
import com.lzt.ssm.blog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author lzt
 * @date 2020/1/8 10:47
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleService articleService;

    @Override
    public List<User> listEntity() {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause("user_status desc");
        List<User> userList = userMapper.selectByExample(userExample);
        for (User user : userList) {
            int count = articleService.countArticleByUserAndStatus(user.getUserId(), ArticleStatus.PUBLISH.getValue());
            user.setArticleCount(count);
        }
        return userList;
    }

    @Override
    public User getEntityById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateEntity(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void deleteEntityById(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insertEntity(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public User getEntityByNameOrEmail(String str) {
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(str);
        userExample.or().andUserEmailEqualTo(str);
        userExample.createCriteria().andUserStatusEqualTo(UserStatus.NORMAL.getValue());
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtil.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Override
    public User getEntityByName(String name) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(name);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtil.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Override
    public User getEntityByEmail(String email) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserEmailEqualTo(email);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtil.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    @Override
    public List<User> listInUser() {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserStatusEqualTo(UserStatus.NORMAL.getValue()).andUserTypeEqualTo(UserType.IN.getValue());
        return userMapper.selectByExample(userExample);
    }
}
