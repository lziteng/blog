package com.lzt.ssm.blog.dto;

import lombok.Data;

import java.util.List;

/**
 * 用于添加和修改文章时，接收页面中form表单的值
 *
 * @author lzt
 * @date 2020/1/10 15:19
 */
@Data
public class ArticleParm {
    private Integer articleId;

    private String articleTitle;

    private String articlePhoto;

    private String articleContent;

    private Integer articleParentCategoryId;

    private Integer articleChildCategoryId;

    private Integer articleOrder;

    private Integer articleStatus;

    private List<Integer> articleTagIds;
}
