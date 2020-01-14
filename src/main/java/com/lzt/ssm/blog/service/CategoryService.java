package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.Category;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/9 20:48
 */
public interface CategoryService extends BaseCrudService<Category> {

    /**
     * 获取所有记录
     *
     * @return 记录集合
     */
    List<Category> listEntity();

    /**
     * 根据分类id获取对应的文章数
     *
     * @param categoryId 分类id
     * @return 对应的文章数
     */
    Integer countArticleByCategoryId(Integer categoryId);
}
