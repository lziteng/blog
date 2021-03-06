package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.*;

/**
 * Created by Mybatis Generator on 2020/01/10
 *
 * @author lzt
 */
public interface ArticleMapper extends EntityMoveMapper<Article> {

    /**
     * 根据id删除
     *
     * @param articleId 文章id
     * @return
     */
    int deleteByPrimaryKey(Integer articleId);

    /**
     * 添加文章
     *
     * @param record 文章
     * @return
     */
    int insert(Article record);

    /**
     * 添加文章
     *
     * @param record 文章
     * @return
     */
    int insertSelective(Article record);

    /**
     * 根据id获取文章
     *
     * @param articleId 文章id
     * @return
     */
    Article selectByPrimaryKey(Integer articleId);

    /**
     * 更新文章
     *
     * @param record 文章
     * @return
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * 更新文章
     *
     * @param record 文章
     * @return
     */
    int updateByPrimaryKey(Article record);

    /**
     * 获得所有的文章
     * 由于按排序号、id降序排序，所有将上下移动操作互换
     *
     * @param criteria 查询条件
     * @return 文章列表
     */
    List<Article> listEntity(HashMap<String, Object> criteria);

    /**
     * 文章归档
     * <p>
     * 所有发布状态的文章，同时只查询部分字段
     *
     * @return
     */
    List<Article> listAllNotWithContent();

    /**
     * 获取文章总数
     *
     * @param status 状态
     * @return 数量
     */
    Integer countEntity(@Param(value = "status") Integer status);

    /**
     * 获得评论总数
     *
     * @return 评论总数
     */
    Integer countArticleComment();

    /**
     * 获得浏览量总数
     *
     * @return 文章数量
     */
    Integer countArticleView();

    /**
     * 根据状态和文章id查询文章信息
     *
     * @param status 状态
     * @param id     文章ID
     * @return 文章
     */
    Article getEntityByStatusAndId(@Param(value = "status") Integer status, @Param(value = "id") Integer id);

    /**
     * 获得访问最多的文章(猜你喜欢)
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByViewCount(@Param(value = "limit") Integer limit);

    //

    /**
     * 获得随机文章
     * <p>
     * ORDER By rand()，使得每次检索的结果排序会不同
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listRandomArticle(@Param(value = "limit") Integer limit);

    /**
     * 热评文章
     *
     * @param limit 查询数量
     * @return 文章列表
     */
    List<Article> listArticleByCommentCount(@Param(value = "limit") Integer limit);

    /**
     * 更新文章的评论数(根据comment表进行更新)
     *
     * @param articleId 文章ID
     */
    void updateCommentCount(@Param(value = "articleId") Integer articleId);

    /**
     * 获得最后更新的记录
     *
     * @return 文章
     */
    Article getLastUpdateArticle();

    /**
     * 用户的文章数
     *
     * @param userId 用户ID
     * @param status 文章状态(为null时查询所有)
     * @return 文章数量
     */
    Integer countArticleByUserAndStatus(@Param(value = "userId") Integer userId, @Param("status") Integer status);


    /**
     * 根据分类ID获取对应的所有记录
     *
     * @param categoryIds 分类ID集合
     * @param limit       查询数量
     * @return 对应的文章列表
     */
    List<Article> findArticleByCategoryIds(@Param("categoryIds") List<Integer> categoryIds,
                                           @Param("limit") Integer limit);

    /**
     * 获得最新文章
     *
     * @param limit 查询数量
     * @return 列表
     */
    List<Article> listRecentArticleByLimit(Integer limit);

}