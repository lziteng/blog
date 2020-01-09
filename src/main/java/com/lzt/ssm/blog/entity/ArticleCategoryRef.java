package com.lzt.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章和分类关联表
 *
 * @Author: lzt
 * @Date: 2020/1/9 22:58
 */
@Data
public class ArticleCategoryRef implements Serializable {
    private static final long serialVersionUID = 7180544991233001475L;

    private Integer articleId;

    private Integer categoryId;

    public ArticleCategoryRef() {
    }

    public ArticleCategoryRef(Integer articleId, Integer categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }
}
