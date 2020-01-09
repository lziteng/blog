package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.User;
import com.lzt.ssm.blog.entity.UserExample;
import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/09
 * @author lzt 
 */
public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}