package com.lzt.ssm.blog.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * 可上下移动的mapper
 * 排序要求:升序(排序号小的在前面)
 *
 * @Author: lzt
 * @Date: 2020/1/11 0:20
 */
public interface EntityMoveMapper<T> {

    /**
     * 获取最大排序号
     *
     * @return 最大排序号
     */
    int getMaxOrder();

    /**
     * 根据排序号获取上一条记录
     *
     * @param condition 其他条件(可为null)，如级别
     * @param order     排序号
     * @return 上一条记录
     */
    T getPreEntityByOrder(@Param("condition") Integer condition, @Param("order") Integer order);

    /**
     * 根据排序号获取下一条记录
     *
     * @param condition 其他条件(可为null)，如级别
     * @param order     排序号
     * @return 下一条记录
     */
    T getNextEntityByOrder(@Param("condition") Integer condition, @Param("order") Integer order);
}
