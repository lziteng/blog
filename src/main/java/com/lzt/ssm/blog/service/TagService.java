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

    /**
     * 根据标签id统计对应的文章数
     *
     * @param tagId 标签id
     * @return 对应的文章数
     */
    Integer countArticleByTagId(Integer tagId);

}
