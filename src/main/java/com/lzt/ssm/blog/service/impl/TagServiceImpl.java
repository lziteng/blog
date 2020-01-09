package com.lzt.ssm.blog.service.impl;

import com.lzt.ssm.blog.entity.Tag;
import com.lzt.ssm.blog.entity.TagExample;
import com.lzt.ssm.blog.mapper.TagMapper;
import com.lzt.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/9 20:16
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Override
    public void insertEntity(Tag tag) {
        tagMapper.insertSelective(tag);
    }

    @Override
    public void deleteEntityById(Integer id) {
        tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateEntity(Tag tag) {
        tagMapper.updateByPrimaryKeySelective(tag);
    }

    @Override
    public Tag getEntityById(Integer id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Tag> listEntity() {
        TagExample tagExample = new TagExample();
        return tagMapper.selectByExample(tagExample);
    }
}
