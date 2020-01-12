package com.lzt.ssm.blog.service.impl;

import com.lzt.ssm.blog.entity.Tag;
import com.lzt.ssm.blog.entity.TagExample;
import com.lzt.ssm.blog.mapper.ArticleTagRefMapper;
import com.lzt.ssm.blog.mapper.TagMapper;
import com.lzt.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/9 20:16
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;

    @Override
    public void insertEntity(Tag tag) {
        tagMapper.insertSelective(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteEntityById(Integer id) {
        Integer count = articleTagRefMapper.countArticleByTagId(id);
        if (count > 0) {
            throw  new RuntimeException("该标签下存在文章，不可删除");
        }

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
        List<Tag> tagList = tagMapper.selectByExample(null);
        //填充文章数量属性
        for (Tag tag : tagList) {
            Integer count = articleTagRefMapper.countArticleByTagId(tag.getTagId());
            tag.setArticleCount(count);
        }
        return tagList;
    }
}
