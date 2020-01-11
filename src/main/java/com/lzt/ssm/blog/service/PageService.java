package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.Page;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/11 15:03
 */
public interface PageService extends BaseCrudService<Page> {

    /**
     * 根据key获取对应的记录，用户检测key是否唯一
     *
     * @param key key字符串
     * @return 记录
     */
    Page getEntityByKey(String key);

    /**
     * 根据状态和key获取记录，用于网站中展示内容
     *
     * @param status 状态 (只获取status=1的记录)
     * @param key    key字符串
     * @return 记录
     */
    Page getEntityByStatusAndKey(Integer status, String key);

    /**
     * 获取所有记录
     *
     * @return 所有记录
     */
    List<Page> listEntity();
}
