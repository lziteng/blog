package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.Menu;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/11 10:45
 */
public interface MenuService extends BaseCrudService<Menu>, EntityMoveService<Menu> {

    /**
     * 根据级别获取对应的所有记录
     *
     * @param level 级别(1:顶部菜单 2:主要菜单 null时获取所有)
     * @return 对应的所有记录
     */
    List<Menu> listEntityByLevel(Integer level);
}
