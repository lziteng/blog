package com.lzt.ssm.blog.service;

/**
 * 可上下移动的service(数据库中通过互换排序号实现移动功能)
 * 排序要求:升序(排序号小的在前面)
 *
 * @author lzt
 * @date 2019/12/5 16:47
 */
public interface EntityMoveService<T> {

    /**
     * 获取最大排序号
     *
     * @return 最大排序号
     */
    int getMaxOrder();

    /**
     * 根据排序号获取上一条记录
     *
     * @param condition 其他条件(可为null)，如菜单级别
     * @param order     排序号
     * @return 上一条记录
     */
    T getPreEntityByOrder(Integer condition, Integer order);

    /**
     * 根据排序号获取下一条记录
     *
     * @param condition 其他条件(可为null)，如级别
     * @param order     排序号
     * @return 下一条记录
     */
    T getNextEntityByOrder(Integer condition, Integer order);
}