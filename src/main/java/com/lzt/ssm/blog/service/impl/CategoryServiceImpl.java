package com.lzt.ssm.blog.service.impl;

import com.lzt.ssm.blog.entity.Category;
import com.lzt.ssm.blog.entity.CategoryExample;
import com.lzt.ssm.blog.mapper.CategoryMapper;
import com.lzt.ssm.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/9 20:51
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void insertEntity(Category category) {
        categoryMapper.insertSelective(category);
    }

    @Override
    public void deleteEntityById(Integer id) {
        categoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateEntity(Category category) {
        categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public Category getEntityById(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Category> listEntity() {
        CategoryExample categoryExample = new CategoryExample();
        return categoryMapper.selectByExample(categoryExample);
    }
}
