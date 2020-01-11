package com.lzt.ssm.blog.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.github.pagehelper.*;
import com.lzt.ssm.blog.entity.*;
import com.lzt.ssm.blog.enums.ArticleCommentStatus;
import com.lzt.ssm.blog.mapper.*;
import com.lzt.ssm.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author lzt
 * @date 2020/1/10 14:13
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleCategoryRefMapper articleCategoryRefMapper;

    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;

    @Override
    public int countEntity(Integer status) {
        return articleMapper.countEntity(1);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertEntity(Article article) {
        //添加文章
        article.setArticleCreateTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(ArticleCommentStatus.ALLOW.getValue());
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        Integer maxOrder = articleMapper.getMaxOrder();
        if (maxOrder == null) {
            maxOrder = 0;
        }
        article.setArticleOrder(++maxOrder);
        int i = 1/0;

        articleMapper.insertSelective(article);
        //添加分类和文章的关联记录
        for (Category category : article.getCategoryList()) {
            ArticleCategoryRef articleCategoryRef =
                    new ArticleCategoryRef(article.getArticleId(), category.getCategoryId());
            articleCategoryRefMapper.insert(articleCategoryRef);
        }

        //添加标签和文章的关联记录
        for (Tag tag : article.getTagList()) {
            ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(), tag.getTagId());
            articleTagRefMapper.insert(articleTagRef);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEntityById(Integer articleId) {
        articleMapper.deleteByPrimaryKey(articleId);

        //删除分类和文章的关联记录
        articleCategoryRefMapper.deleteByArticleId(articleId);

        //删除标签和文章的关联记录
        articleTagRefMapper.deleteByArticleId(articleId);
    }

    @Override
    public void updateEntity(Article article) {
        article.setArticleUpdateTime(new Date());
        articleMapper.updateByPrimaryKeySelective(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateEntityDetail(Article article) {
        article.setArticleUpdateTime(new Date());
        articleMapper.updateByPrimaryKeySelective(article);

        if (CollectionUtil.isNotEmpty(article.getCategoryList())) {
            //先删除分类和文章的关联记录
            articleCategoryRefMapper.deleteByArticleId(article.getArticleId());

            //再添加分类和文章的关联记录
            for (Category category : article.getCategoryList()) {
                ArticleCategoryRef articleCategoryRef =
                        new ArticleCategoryRef(article.getArticleId(), category.getCategoryId());
                articleCategoryRefMapper.insert(articleCategoryRef);
            }
        }

        if (CollectionUtil.isNotEmpty(article.getTagList())) {
            //先删除标签和文章的关联记录
            articleTagRefMapper.deleteByArticleId(article.getArticleId());

            //再添加标签和文章的关联记录
            for (Tag tag : article.getTagList()) {
                ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(), tag.getTagId());
                articleTagRefMapper.insert(articleTagRef);
            }
        }
    }

    @Override
    public Article getEntityByStatusAndId(Integer status, Integer id) {
        Article article = articleMapper.getEntityByStatusAndId(status, id);

        if (article != null) {
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(article.getArticleId());
            article.setCategoryList(categoryList);

            List<Tag> tagList = articleTagRefMapper.listTagByArticleId(article.getArticleId());
            article.setTagList(tagList);
        }

        return article;
    }

    @Override
    public PageInfo<Article> pageEntity(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Article> articleList = articleMapper.listEntity(criteria);
        for (Article article : articleList) {
            //填充CategoryList
            List<Category> categoryList = articleCategoryRefMapper.listCategoryByArticleId(article.getArticleId());
            if (CollectionUtil.isEmpty(categoryList)) {
                categoryList = new ArrayList<>(1);
                categoryList.add(Category.Default());
            }
            article.setCategoryList(categoryList);

            //填充TagList
            List<Tag> tagList = articleTagRefMapper.listTagByArticleId(article.getArticleId());
            if (CollectionUtil.isEmpty(tagList)) {
                tagList = new ArrayList<>(1);
                tagList.add(Tag.Default());
            }
            article.setTagList(tagList);
        }
        return new PageInfo<>(articleList);
    }

    @Override
    public int getMaxOrder() {
        return articleMapper.getMaxOrder();
    }

    @Override
    public Article getPreEntityByOrder(Integer condition,Integer order) {
        return articleMapper.getPreEntityByOrder(condition,order);
    }

    @Override
    public Article getNextEntityByOrder(Integer condition,Integer order) {
        return articleMapper.getNextEntityByOrder(condition,order);
    }
}
