package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.User;
import com.lzt.ssm.blog.entity.UserExample;

import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/09
 *
 * @author lzt
 */
public interface UserMapper {

    /**
     * 获取记录总数
     *
     * @param example 查询对象
     * @return 记录总数
     */
    long countByExample(UserExample example);

    /**
     * 根据id删除记录
     *
     * @param userId id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer userId);

    /**
     * 添加记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int insert(User record);

    /**
     * 添加记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(User record);

    /**
     * 根据条件获取对应的所有记录
     *
     * @param example 查询对象
     * @return 对应的所有记录
     */
    List<User> selectByExample(UserExample example);

    /**
     * 根据id获取记录
     *
     * @param userId id
     * @return 记录
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(User record);
}