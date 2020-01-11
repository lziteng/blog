package com.lzt.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Mybatis Generator on 2020/01/10
 *
 * @author lzt
 */
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = -4426501159181325646L;
    private Integer articleId;

    private Integer articleUserId;

    private String articleTitle;

    private String articleContent;

    private Integer articleViewCount;

    private Integer articleCommentCount;

    private Integer articleLikeCount;

    /**
     * 是否可评价
     */
    private Integer articleIsComment;

    private Integer articleStatus;

    private Integer articleOrder;

    private Date articleUpdateTime;

    private Date articleCreateTime;

    private String articleSummary;

    /**
     * 所属用户
     */
    private User user;

    /**
     * 所属标签集合
     */
    private List<Tag> tagList;

    /**
     * 所属分类集合
     */
    private List<Category> categoryList;
}