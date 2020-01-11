package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Options;

/**
 * Created by Mybatis Generator on 2020/01/10
 *
 * @author lzt
 */
public interface OptionsMapper {

    /**
     * 删除
     *
     * @param optionId id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer optionId);

    /**
     * 添加
     *
     * @param record 记录
     * @return 影响行数
     */
    int insert(Options record);

    /**
     * 添加
     *
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Options record);

    /**
     * 根据id查询记录
     *
     * @param optionId id
     * @return 记录
     */
    Options selectByPrimaryKey(Integer optionId);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Options record);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Options record);

    /**
     * 获取默认的系统配置信息
     *
     * @return 记录
     */
    Options getDefaultEntity();
}