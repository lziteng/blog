package com.lzt.ssm.blog.service;

import com.github.pagehelper.PageInfo;
import com.lzt.ssm.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

/**
 * @author lzt
 * @date 2020/1/10 13:50
 */
public interface ArticleService extends BaseCrudService<Article>, EntityMoveService<Article> {

    /**
     * 根据状态获取对应的文章总数
     *
     * @param status 状态(0:草稿 1:发布 null时获取所有)
     * @return 对应的文章总数
     */
    int countEntity(Integer status);

    /**
     * 修改文章详细信息(与标签、分类的关系)
     *
     * @param article 文章
     */
    void updateEntityDetail(Article article);

    /**
     * 根据状态和文章id查询文章信息
     *
     * @param status    状态
     * @param articleId 文章ID
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

    /**
     * 获取最新发布的文章列表
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listRecentArticle(Integer limit);

    /**
     * 获得最后更新的记录
     *
     * @return 文章
     */
    Article getLastUpdateArticle();


    /**
     * 根据id获取对应的分类id集合
     *
     * @param articleId id
     * @return 分类id集合
     */
    List<Integer> listCategoryIdsByArticleId(Integer articleId);


    /**
     * 根据分类id集合获取对应的所有记录
     *
     * @param categoryIds 分类id集合
     * @param limit       查询数据
     * @return 对应的所有记录
     */
    List<Article> listArticleByCategoryIds(List<Integer> categoryIds, Integer limit);

    /**
     * 获得访问最多的文章(猜你喜欢)
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByViewCount(Integer limit);

    /**
     * 获得随机文章
     * <p>
     * ORDER By rand()，使得每次检索的结果排序会不同
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listRandomArticle(Integer limit);

    /**
     * 文章归档
     * <p>
     * 所有发布状态的文章，同时只查询部分字段
     *
     * @return
     */
    List<Article> listAllNotWithContent();
}