package com.lzt.ssm.blog.entity;

import java.util.Date;
import lombok.Data;

/**
 * Created by Mybatis Generator on 2020/01/11
 * @author lzt 
 */
@Data
public class Page {
    private Integer pageId;

    private String pageKey;

    private String pageTitle;

    private String pageContent;

    private Date pageCreateTime;

    private Date pageUpdateTime;

    private Integer pageViewCount;

    private Integer pageCommentCount;

    private Integer pageStatus;
}