package com.lzt.ssm.blog.dto;

import lombok.Data;

/**
 * VO（View Object）：视图对象，用于展示层，它的作用是把某个指定页面（或组件）的所有数据封装起来。
 *
 * @author lzt
 * @date 2020/1/9 10:04
 */
@Data
public class UploadFileVO {

    private String src;

    private String title;
}
