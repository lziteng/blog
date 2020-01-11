package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Notice;
import com.lzt.ssm.blog.entity.NoticeExample;

import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/09
 *
 * @author lzt
 */
public interface NoticeMapper extends EntityMoveMapper<Notice> {

    /**
     * 获取记录总数
     *
     * @param example 查询对象
     * @return 记录总数
     */
    long countByExample(NoticeExample example);

    /**
     * 根据id删除记录
     *
     * @param noticeId id
     * @return 影响行数
     */
    int deleteByPrimaryKey(Integer noticeId);

    /**
     * 添加记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int insert(Notice record);

    /**
     * 添加记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int insertSelective(Notice record);

    /**
     * 根据条件获取对应的所有记录
     *
     * @param example 查询对象
     * @return 对应的所有记录
     */
    List<Notice> selectByExample(NoticeExample example);

    /**
     * 根据id获取记录
     *
     * @param noticeId id
     * @return 记录
     */
    Notice selectByPrimaryKey(Integer noticeId);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(Notice record);

    /**
     * 更新记录
     *
     * @param record 记录
     * @return 影响行数
     */
    int updateByPrimaryKey(Notice record);
}