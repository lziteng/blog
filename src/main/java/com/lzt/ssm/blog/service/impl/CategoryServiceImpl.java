package com.lzt.ssm.blog.service.impl;

import com.lzt.ssm.blog.entity.Category;
import com.lzt.ssm.blog.entity.CategoryExample;
import com.lzt.ssm.blog.mapper.ArticleCategoryRefMapper;
import com.lzt.ssm.blog.mapper.CategoryMapper;
import com.lzt.ssm.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/9 20:51
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Override
    public void insertEntity(Category category) {
        categoryMapper.insertSelective(category);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEntityById(Integer id) {
        Integer count = articleCategoryRefMapper.countArticleByCategoryId(id);
        if (count > 0) {
            throw new RuntimeException("该分类下存在文章，不可删除");
        }
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
        List<Category> categoryList = categoryMapper.selectByExample(null);
        for (Category category : categoryList) {
            Integer count = articleCategoryRefMapper.countArticleByCategoryId(category.getCategoryId());
            category.setArticleCount(count);
        }

        return categoryList;
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        return articleCategoryRefMapper.countArticleByCategoryId(categoryId);
    }
}
