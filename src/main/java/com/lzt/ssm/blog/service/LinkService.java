package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.Link;

import java.util.List;

/**
 * @author lzt
 * @date 2020/1/9 11:28
 */
public interface LinkService extends EntityMoveService<Link>{

    void insertEntity(Link link);

    void deleteEntityById(Integer linkId);

    void updateEntity(Link link);

    Link getEntityById(Integer linkId);

    List<Link> listEntity(Integer status);

    Long countEntity(Integer status);
}