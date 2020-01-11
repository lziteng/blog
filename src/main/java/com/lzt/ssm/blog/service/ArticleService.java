package com.lzt.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.lzt.ssm.blog.entity.Article;

import java.util.HashMap;

/**
 * @author lzt
 * @date 2020/1/10 13:50
 */
public interface ArticleService extends EntityMoveService<Article>{

    /**
     * 根据状态获取对应的文章总数
     *
     * @param status 状态(0:草稿 1:发布 null时获取所有)
     * @return 对应的文章总数
     */
    int countEntity(Integer status);

    /**
     * 添加文章
     *
     * @param article 文章
     */
    void insertEntity(Article article);

    /**
     * 删除文章
     *
     * @param articleId 文章id
     */
    void deleteEntityById(Integer articleId);

    /**
     * 修改文章简单信息
     *
     * @param article 文章
     */
    void updateEntity(Article article);

    /**
     * 修改文章详细信息(与标签、分类的关系)
     *
     * @param article 文章
     */
    void updateEntityDetail(Article article);

    /**
     * 根据状态和文章id查询文章信息
     *
     * @param status 状态
     * @param articleId     文章ID
     * @return 文章
     */
    Article getEntityByStatusAndId(Integer status, Integer articleId);

    /**
     * 分页显示
     *
     * @param pageIndex 第几页开始
     * @param pageSize  一页显示多少
     * @param criteria  查询条件
     * @return 文章列表
     */
    PageInfo<Article> pageEntity(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria);

}