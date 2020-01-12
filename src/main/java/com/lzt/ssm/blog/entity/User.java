package com.lzt.ssm.blog.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Created by Mybatis Generator on 2020/01/12
 *
 * @author lzt
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -4043443552482052017L;

    private Integer userId;

    private String userName;

    private String userPass;

    private String userNickname;

    private String userEmail;

    private String userUrl;

    private String userAvatar;

    private String userLastLoginIp;

    private Date userRegisterTime;

    private Date userLastLoginTime;

    private Integer userStatus;

    /**
     * 1：内网用户 0：外网用户
     */
    private Integer userType;

    /**
     * 文章数量（不是数据库字段）
     */
    private Integer articleCount;
}