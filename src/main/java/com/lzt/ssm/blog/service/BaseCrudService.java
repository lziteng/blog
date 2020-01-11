package com.lzt.ssm.blog.service;

/**
 * 通用的curd操作service
 *
 * @Author: lzt
 * @Date: 2020/1/11 10:48
 */
public interface BaseCrudService<T> {

    /**
     * 添加记录
     *
     * @param t 记录实体
     */
    void insertEntity(T t);

    /**
     * 根据id删除记录
     *
     * @param id id
     */
    void deleteEntityById(Integer id);

    /**
     * 更新记录
     *
     * @param t 记录
     */
    void updateEntity(T t);

    /**
     * 根据id获取记录
     *
     * @param id
     * @return
     */
    T getEntityById(Integer id);
}
