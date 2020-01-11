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
}
