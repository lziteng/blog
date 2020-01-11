package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.Tag;

import java.util.List;

/**
 * @Author: lzt
 * @Date: 2020/1/9 20:14
 */
public interface TagService extends BaseCrudService<Tag> {


    /**
     * 获取所有的记录
     *
     * @return 所有记录
     */
    List<Tag> listEntity();

}
