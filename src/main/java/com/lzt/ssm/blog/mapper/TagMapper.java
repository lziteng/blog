package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Tag;
import com.lzt.ssm.blog.entity.TagExample;

import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/09
 *
 * @author lzt
 */
public interface TagMapper {

    /**
     * 获取记录总数
     *
     * @param example 查询对象
     * @return 记录总数
     */
    long countByExample(TagExample example);

    /**
     * 根据id删除记录
     *
     * @param tagId id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer tagId);

    /**
     * 添加记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int insert(Tag record);

    /**
     * 添加记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Tag record);

    /**
     * 根据条件获取对应的所有记录
     *
     * @param example 查询对象
     * @return 对应的所有记录
     */
    List<Tag> selectByExample(TagExample example);

    /**
     * 根据id获取记录
     *
     * @param tagId id
     * @return 记录
     */
    Tag selectByPrimaryKey(Integer tagId);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Tag record);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Tag record);
}