package com.lzt.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Mybatis Generator on 2020/01/09
 *
 * @author lzt
 */
@Data
public class Category implements Serializable {
    private static final long serialVersionUID = -3984362043798548489L;
    private Integer categoryId;

    private Integer categoryPid;

    private String categoryName;

    private String categoryDescription;

    private Integer categoryOrder;

    private String categoryIcon;

    /**
     * 文章数量(非数据库字段)
     */
    private Integer articleCount;
}