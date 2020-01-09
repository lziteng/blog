package com.lzt.ssm.blog.service.impl;

import com.lzt.ssm.blog.entity.*;
import com.lzt.ssm.blog.mapper.*;
import com.lzt.ssm.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lzt
 * @date 2020/1/9 15:23
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    private NoticeMoveMapper noticeMoveMapper;

    @Override
    public void insertEntity(Notice notice) {
        noticeMapper.insertSelective(notice);
    }

    @Override
    public void deleteEntityById(Integer noticeId) {
        noticeMapper.deleteByPrimaryKey(noticeId);
    }

    @Override
    public void updateEntity(Notice notice) {
        noticeMapper.updateByPrimaryKeySelective(notice);
    }

    @Override
    public Notice getEntityById(Integer noticeId) {
        return noticeMapper.selectByPrimaryKey(noticeId);
    }

    @Override
    public List<Notice> listEntity(Integer status) {
        NoticeExample noticeExample = new NoticeExample();
        if (status != null) {
            noticeExample.createCriteria().andNoticeStatusEqualTo(status);
        }
        noticeExample.setOrderByClause(" notice_order asc ");
        return noticeMapper.selectByExample(noticeExample);
    }

    @Override
    public Integer getMaxOrder() {
        return noticeMoveMapper.getMaxOrder();
    }

    @Override
    public Notice preEntityByOrder(int order) {
        return noticeMoveMapper.preEntityByOrder(order);
    }

    @Override
    public Notice nextEntityByOrder(int order) {
        return noticeMoveMapper.nextEntityByOrder(order);
    }
}
