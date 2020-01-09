package com.lzt.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Mybatis Generator on 2020/01/09
 * @author lzt 
 */
@Data
public class Tag implements Serializable {
    private static final long serialVersionUID = 2097388074348614296L;
    private Integer tagId;

    private String tagName;

    private String tagDescription;

    /**
     * 文章数量(非数据库字段)
     */
    private Integer articleCount;
}