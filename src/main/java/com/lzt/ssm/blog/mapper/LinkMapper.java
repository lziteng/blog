package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Link;
import com.lzt.ssm.blog.entity.LinkExample;

import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/09
 *
 * @author lzt
 */
public interface LinkMapper extends EntityMoveMapper<Link> {

    /**
     * 获取记录总数
     *
     * @param example 查询对象
     * @return 记录总数
     */
    long countByExample(LinkExample example);

    /**
     * 根据id删除记录
     *
     * @param linkId id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer linkId);

    /**
     * 添加记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int insert(Link record);

    /**
     * 添加记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Link record);

    /**
     * 根据条件获取对应的所有记录
     *
     * @param example 查询对象
     * @return 对应的所有记录
     */
    List<Link> selectByExample(LinkExample example);

    /**
     * 根据id获取记录
     *
     * @param linkId id
     * @return 记录
     */
    Link selectByPrimaryKey(Integer linkId);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Link record);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Link record);
}