package com.lzt.ssm.blog.mapper;

import com.lzt.ssm.blog.entity.Page;
import com.lzt.ssm.blog.entity.PageExample;
import java.util.List;

/**
 * Created by Mybatis Generator on 2020/01/11
 * @author lzt 
 */
public interface PageMapper {
    int deleteByPrimaryKey(Integer pageId);

    int insert(Page record);

    int insertSelective(Page record);

    List<Page> selectByExample(PageExample example);

    Page selectByPrimaryKey(Integer pageId);

    int updateByPrimaryKeySelective(Page record);

    int updateByPrimaryKey(Page record);
}