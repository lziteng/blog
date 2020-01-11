package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Category;
import com.lzt.ssm.blog.entity.CategoryExample;

import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/09
 *
 * @author lzt
 */
public interface CategoryMapper {

    /**
     * 获取记录总数
     *
     * @param example 查询对象
     * @return 记录总数
     */
    long countByExample(CategoryExample example);

    /**
     * 根据id删除记录
     *
     * @param categoryId id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer categoryId);

    /**
     * 添加记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int insert(Category record);

    /**
     * 添加记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Category record);

    /**
     * 根据条件获取对应的所有记录
     *
     * @param example 查询对象
     * @return 对应的所有记录
     */
    List<Category> selectByExample(CategoryExample example);

    /**
     * 根据id获取记录
     *
     * @param categoryId id
     * @return 记录
     */
    Category selectByPrimaryKey(Integer categoryId);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Category record);
}