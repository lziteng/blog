package com.lzt.ssm.blog.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mybatis Generator on 2020/01/09
 * @author lzt 
 */
@Data
public class Notice implements Serializable {
    private static final long serialVersionUID = -7711763377304925050L;
    private Integer noticeId;

    private String noticeTitle;

    private String noticeContent;

    private Date noticeCreateTime;

    private Date noticeUpdateTime;

    private Integer noticeStatus;

    private Integer noticeOrder;
}