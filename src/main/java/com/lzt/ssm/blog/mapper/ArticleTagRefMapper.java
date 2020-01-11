package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.*;

import java.util.List;

/**
 * @author lzt
 * @date 2020/1/10 10:10
 */
public interface ArticleTagRefMapper {

    /**
     * 添加文章和标签的关联记录
     *
     * @param record 关联记录
     * @return
     */
    int insert(ArticleTagRef record);

    /**
     * 根据标签id删除记录
     *
     * @param tagId 标签id
     * @return
     */
    int deleteByTagId(Integer tagId);

    /**
     * 根据文章id删除记录
     *
     * @param articleId 文章id
     * @return
     */
    int deleteByArticleId(Integer articleId);

    /**
     * 根据标签id统计文章数
     *
     * @param tagId 标签id
     * @return
     */
    int countArticleByTagId(Integer tagId);

    /**
     * 根据文章id获取标签列表
     *
     * @param articleId 文章id
     * @return
     */
    List<Tag> listTagByArticleId(Integer articleId);
}