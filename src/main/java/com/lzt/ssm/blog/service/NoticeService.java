package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.Notice;

import java.util.List;

/**
 * @author lzt
 * @date 2020/1/9 15:16
 */
public interface NoticeService extends BaseCrudService<Notice>, EntityMoveService<Notice> {

    /**
     * 根据状态获取对应的所有记录
     *
     * @param status 状态(0:隐藏 1:显示 null时获取所有)
     * @return 对应的所有记录
     */
    List<Notice> listEntity(Integer status);

    /**
     * 根据状态和id获取记录
     *
     * @param status   状态
     * @param noticeId id
     * @return 记录
     */
    Notice getEntityByStatusAndId(Integer status, Integer noticeId);
}