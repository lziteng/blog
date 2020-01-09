package com.lzt.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章和标签关联表
 *
 * @Author: lzt
 * @Date: 2020/1/9 23:00
 */
@Data
public class ArticleTagRef implements Serializable {
    private static final long serialVersionUID = -255689959058020352L;

    private Integer articleId;

    private Integer tagId;

    public ArticleTagRef() {
    }

    public ArticleTagRef(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
