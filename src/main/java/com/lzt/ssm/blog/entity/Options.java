package com.lzt.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 站点配置信息
 * Created by Mybatis Generator on 2020/01/10
 *
 * @author lzt
 */
@Data
public class Options implements Serializable {

    private static final long serialVersionUID = 9076546602791069853L;

    private Integer optionId;

    private String optionSiteTitle;

    private String optionSiteDescrption;

    private String optionMetaDescrption;

    private String optionMetaKeyword;

    private String optionAboutsiteAvatar;

    private String optionAboutsiteTitle;

    private String optionAboutsiteContent;

    private String optionAboutsiteWechat;

    private String optionAboutsiteQq;

    private String optionAboutsiteGithub;

    private String optionAboutsiteWeibo;

    private String optionTongji;

    private Integer optionStatus;
}