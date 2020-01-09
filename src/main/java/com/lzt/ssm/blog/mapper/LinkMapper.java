package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Link;
import com.lzt.ssm.blog.entity.LinkExample;
import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/09
 * @author lzt 
 */
public interface LinkMapper {
    long countByExample(LinkExample example);

    int deleteByPrimaryKey(Integer linkId);

    int insert(Link record);

    int insertSelective(Link record);

    List<Link> selectByExample(LinkExample example);

    Link selectByPrimaryKey(Integer linkId);

    int updateByPrimaryKeySelective(Link record);

    int updateByPrimaryKey(Link record);
}