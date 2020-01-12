package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.*;

import java.util.List;

/**
 * @author lzt
 * @date 2020/1/10 9:35
 */
public interface ArticleCategoryRefMapper {

    /**
     * 添加文章和分类的关联记录
     *
     * @param record 关联记录
     * @return 影响行数
     */
    int insert(ArticleCategoryRef record);

    /**
     * 根据分类id删除对应的记录
     *
     * @param categoryId 分类id
     * @return 影响行数
     */
    int deleteByCategoryId(Integer categoryId);

    /**
     * 根据文章id删除记录
     *
     * @param articleId 文章id
     * @return 影响行数
     */
    int deleteByArticleId(Integer articleId);


    /**
     * 根据分类id统计文章数
     *
     * @param categoryId 分类id
     * @return 统计文章数
     */
    int countArticleByCategoryId(Integer categoryId);

    /**
     * 根据文章id查询分类id集合
     *
     * @param articleId 文章id
     * @return 分类id集合
     */
    List<Integer> selectCategoryIdByArticleId(Integer articleId);

    /**
     * 根据分类id查询文章id集合
     *
     * @param categoryId 分类id
     * @return 文章id集合
     */
    List<Integer> selectArticleIdByCategoryId(Integer categoryId);

    /**
     * 根据文章id获取分类列表
     *
     * @param articleId 文章id
     * @return 分类列表
     */
    List<Category> listCategoryByArticleId(Integer articleId);


}