package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/10
 *
 * @author lzt
 */
public interface MenuMapper extends EntityMoveMapper<Menu> {

    /**
     * 根据id删除记录
     *
     * @param menuId id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer menuId);

    /**
     * 添加记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int insert(Menu record);

    /**
     * 添加记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Menu record);

    /**
     * 根据id获取记录
     *
     * @param menuId id
     * @return 记录
     */
    Menu selectByPrimaryKey(Integer menuId);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Menu record);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return
     */
    int updateByPrimaryKey(Menu record);

    /**
     * 根据级别获取对应的所有记录
     *
     * @param level 级别(1:顶部菜单 2:主要菜单 null时获取所有)
     * @return 对应的所有记录
     */
    List<Menu> listEntityByLevel(@Param("level") Integer level);
}