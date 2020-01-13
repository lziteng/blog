package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Comment;
import com.lzt.ssm.blog.entity.CommentExample;
import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/13
 * @author lzt 
 */
public interface CommentMapper {
    long countByExample(CommentExample example);

    int deleteByExample(CommentExample example);

    int deleteByPrimaryKey(Integer commentId);

    int insert(Comment record);

    int insertSelective(Comment record);

    List<Comment> selectByExample(CommentExample example);

    Comment selectByPrimaryKey(Integer commentId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);
}