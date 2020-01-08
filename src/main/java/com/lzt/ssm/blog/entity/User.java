package com.lzt.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mybatis Generator on 2020/01/08
 *
 * @author lzt
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 7387807527730790753L;
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
}