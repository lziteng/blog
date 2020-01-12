package com.lzt.ssm.blog.service.impl;

import cn.hutool.core.collection.CollectionUtil;
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
    public Notice getEntityByStatusAndId(Integer status, Integer noticeId) {
        NoticeExample noticeExample = new NoticeExample();
        noticeExample.createCriteria().andNoticeStatusEqualTo(status).andNoticeIdEqualTo(noticeId);
        List<Notice> noticeList = noticeMapper.selectByExample(noticeExample);
        if (CollectionUtil.isEmpty(noticeList)) {
            return null;
        }
        return noticeList.get(0);
    }

    @Override
    public int getMaxOrder() {
        return noticeMapper.getMaxOrder();
    }

    @Override
    public Notice getPreEntityByOrder(Integer condition, Integer order) {
        return noticeMapper.getPreEntityByOrder(condition, order);
    }

    @Override
    public Notice getNextEntityByOrder(Integer condition, Integer order) {
        return noticeMapper.getNextEntityByOrder(condition, order);
    }
}
