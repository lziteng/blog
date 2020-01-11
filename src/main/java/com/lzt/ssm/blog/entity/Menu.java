package com.lzt.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Mybatis Generator on 2020/01/10
 *
 * @author lzt
 */
@Data
public class Menu implements Serializable {

    private static final long serialVersionUID = -6497179560811756342L;

    private Integer menuId;

    private String menuName;

    private String menuUrl;

    /**
     * 顶部菜单:1
     * 主要菜单:2
     */
    private Integer menuLevel;

    private String menuIcon;

    private Integer menuOrder;
}