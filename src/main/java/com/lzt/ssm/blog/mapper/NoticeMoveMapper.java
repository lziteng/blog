package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Notice;

/**
 * @author lzt
 * @date 2020/1/9 15:21
 */
public interface NoticeMoveMapper {
    Integer getMaxOrder();

    Notice preEntityByOrder(int order);

    Notice nextEntityByOrder(int order);
}