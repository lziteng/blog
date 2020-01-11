package com.lzt.ssm.blog.service;

import com.lzt.ssm.blog.entity.Options;

/**
 * @Author: lzt
 * @Date: 2020/1/10 21:42
 */
public interface OptionsService extends BaseCrudService<Options> {

    /**
     * 获取默认的记录
     *
     * @return 默认记录(status为1的第一条记录)
     */
    Options getDefaultEntity();
}
