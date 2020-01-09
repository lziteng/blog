package com.lzt.ssm.blog.service.impl;

import com.lzt.ssm.blog.entity.*;
import com.lzt.ssm.blog.mapper.*;
import com.lzt.ssm.blog.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzt
 * @date 2020/1/9 11:42
 */
@Service
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Autowired
    private LinkMoveMapper linkMoveMapper;

    public LinkServiceImpl() {
    }

    @Override
    public void insertEntity(Link link) {
        linkMapper.insertSelective(link);
    }

    @Override
    public void deleteEntityById(Integer linkId) {
        linkMapper.deleteByPrimaryKey(linkId);
    }

    @Override
    public void updateEntity(Link link) {
        linkMapper.updateByPrimaryKeySelective(link);
    }

    @Override
    public Link getEntityById(Integer linkId) {
        return linkMapper.selectByPrimaryKey(linkId);
    }

    @Override
    public List<Link> listEntity(Integer status) {
        LinkExample linkExample = new LinkExample();
        if (status != null) {
            linkExample.createCriteria().andLinkStatusEqualTo(status);
        }
        linkExample.setOrderByClause(" link_order asc ");
        return linkMapper.selectByExample(linkExample);
    }

    @Override
    public Long countEntity(Integer status) {
        LinkExample linkExample = new LinkExample();
        if (status != null) {
            linkExample.createCriteria().andLinkStatusEqualTo(status);
        }

        return linkMapper.countByExample(linkExample);
    }

    @Override
    public Integer getMaxOrder() {
        return linkMoveMapper.getMaxOrder();
    }

    @Override
    public Link preEntityByOrder(int order) {
        return linkMoveMapper.preEntityByOrder(order);
    }

    @Override
    public Link nextEntityByOrder(int order) {
        return linkMoveMapper.nextEntityByOrder(order);
    }
}
