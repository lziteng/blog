package com.lzt.ssm.blog.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.lzt.ssm.blog.entity.Page;
import com.lzt.ssm.blog.entity.PageExample;
import com.lzt.ssm.blog.mapper.PageMapper;
import com.lzt.ssm.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/11 15:09
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageMapper pageMapper;

    @Override
    public Page getEntityByKey(String key) {
        PageExample pageExample = new PageExample();
        pageExample.createCriteria().andPageKeyEqualTo(key);
        List<Page> pageList = pageMapper.selectByExample(pageExample);
        if (CollectionUtil.isEmpty(pageList)) {
            return null;
        } else {
            return pageList.get(0);
        }
    }

    @Override
    public Page getEntityByStatusAndKey(Integer status, String key) {
        PageExample pageExample = new PageExample();
        pageExample.createCriteria().andPageStatusEqualTo(status).andPageKeyEqualTo(key);
        List<Page> pageList = pageMapper.selectByExample(pageExample);
        if (CollectionUtil.isEmpty(pageList)) {
            return null;
        } else {
            return pageList.get(0);
        }
    }

    @Override
    public List<Page> listEntity() {
        return pageMapper.selectByExample(null);
    }

    @Override
    public void insertEntity(Page page) {
        pageMapper.insertSelective(page);
    }

    @Override
    public void deleteEntityById(Integer id) {
        pageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateEntity(Page page) {
        pageMapper.updateByPrimaryKeySelective(page);
    }

    @Override
    public Page getEntityById(Integer id) {
        return pageMapper.selectByPrimaryKey(id);
    }
}
