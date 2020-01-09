package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Tag;
import com.lzt.ssm.blog.entity.TagExample;
import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/09
 * @author lzt 
 */
public interface TagMapper {
    long countByExample(TagExample example);

    int deleteByPrimaryKey(Integer tagId);

    int insert(Tag record);

    int insertSelective(Tag record);

    List<Tag> selectByExample(TagExample example);

    Tag selectByPrimaryKey(Integer tagId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}