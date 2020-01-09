package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.Category;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/9 20:48
 */
public interface CategoryService {

    void insertEntity(Category category);

    void deleteEntityById(Integer id);

    void updateEntity(Category category);

    Category getEntityById(Integer id);

    List<Category> listEntity();
}
