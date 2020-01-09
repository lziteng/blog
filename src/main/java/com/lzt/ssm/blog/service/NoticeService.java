package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.Notice;

import java.util.List;

/**
 * @author lzt
 * @date 2020/1/9 15:16
 */
public interface NoticeService extends EntityMoveService<Notice> {
     void insertEntity(Notice notice);

     void deleteEntityById(Integer noticeId);

     void updateEntity(Notice notice);

     Notice getEntityById(Integer noticeId);

     List<Notice> listEntity(Integer status);
}