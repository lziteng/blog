package com.lzt.ssm.blog.service;

/**
 * 可上下移动的service
 *
 * @author lzt
 * @date 2019/12/5 16:47
 */
public interface EntityMoveService<T> {

    Integer getMaxOrder();

    T preEntityByOrder(int order);

    T nextEntityByOrder(int order);
}