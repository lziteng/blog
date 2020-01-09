package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.Tag;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/9 20:14
 */
public interface TagService {

    void insertEntity(Tag tag);

    void deleteEntityById(Integer id);

    void updateEntity(Tag tag);

    Tag getEntityById(Integer id);

    List<Tag> listEntity();

}
