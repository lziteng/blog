package com.lzt.ssm.blog.service.impl;

import com.lzt.ssm.blog.entity.Options;
import com.lzt.ssm.blog.mapper.OptionsMapper;
import com.lzt.ssm.blog.service.OptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lzt
 * @Date: 2020/1/10 21:46
 */
@Service
public class OptionsServiceImpl implements OptionsService {

    @Autowired
    private OptionsMapper optionsMapper;

    @Override
    public void insertEntity(Options options) {
        optionsMapper.insertSelective(options);
    }

    @Override
    public void deleteEntityById(Integer optionId) {
        optionsMapper.deleteByPrimaryKey(optionId);
    }

    @Override
    public void updateEntity(Options options) {
        optionsMapper.updateByPrimaryKeySelective(options);
    }

    @Override
    public Options getEntityById(Integer optionId) {
        return optionsMapper.selectByPrimaryKey(optionId);
    }

    @Override
    public Options getDefaultEntity() {
        return optionsMapper.getDefaultEntity();
    }
}
