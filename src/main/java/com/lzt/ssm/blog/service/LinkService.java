package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.Link;

import java.util.List;

/**
 * @author lzt
 * @date 2020/1/9 11:28
 */
public interface LinkService extends BaseCrudService<Link>, EntityMoveService<Link> {

    /**
     * 根据状态获取对应的所有记录
     *
     * @param status 状态(0:隐藏 1:显示 null时获取所有)
     * @return 对应的所有记录
     */
    List<Link> listEntity(Integer status);

    /**
     * 根据状态获取对应的记录总数
     *
     * @param status 状态(0:隐藏 1:显示 null时获取所有)
     * @return 对应的记录总数
     */
    Long countEntity(Integer status);
}