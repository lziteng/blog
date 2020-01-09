package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Category;
import com.lzt.ssm.blog.entity.CategoryExample;
import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/09
 * @author lzt 
 */
public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer categoryId);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}