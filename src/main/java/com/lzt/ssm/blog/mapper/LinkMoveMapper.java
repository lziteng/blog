package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Link;

/**
 * @author lzt
 * @date 2020/1/9 14:24
 */
public interface LinkMoveMapper {
    Integer getMaxOrder();

    Link preEntityByOrder(int order);

    Link nextEntityByOrder(int order);
}