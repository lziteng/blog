package com.lzt.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mybatis Generator on 2020/01/13
 *
 * @author lzt
 */
@Data
public class Comment implements Serializable {

    private static final long serialVersionUID = -783569794450571329L;

    private Integer commentId;

    private Integer commentPid;

    /**
     * 父评论者的名称
     */
    private String commentPname;

    private Integer commentArticleId;

    private String commentAuthorName;

    private String commentAuthorEmail;

    private String commentAuthorUrl;

    private String commentAuthorAvatar;

    private String commentContent;

    private String commentAgent;

    private String commentIp;

    private Date commentCreateTime;

    /**
     * 角色(1:博主 0:访客)
     */
    private Integer commentRole;

    private Article article;
}