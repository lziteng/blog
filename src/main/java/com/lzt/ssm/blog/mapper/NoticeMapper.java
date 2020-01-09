package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Notice;
import com.lzt.ssm.blog.entity.NoticeExample;
import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/09
 * @author lzt 
 */
public interface NoticeMapper {
    long countByExample(NoticeExample example);

    int deleteByPrimaryKey(Integer noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    List<Notice> selectByExample(NoticeExample example);

    Notice selectByPrimaryKey(Integer noticeId);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);
}