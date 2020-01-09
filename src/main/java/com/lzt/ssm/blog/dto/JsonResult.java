package com.lzt.ssm.blog.dto;

import lombok.Data;

/**
 * DTO（Data Transfer Object）：泛指用于展示层与服务层之间的数据传输对象。
 *
 * @author lzt
 * @date 2020/1/9 10:00
 */
@Data
public class JsonResult<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回的具体信息
     */
    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult fail() {
        return new JsonResult(1, "操作失败", null);
    }

    public JsonResult fail(String msg) {
        return new JsonResult(1, msg, null);
    }

    public JsonResult ok() {
        return new JsonResult(0, "操作成功", null);
    }

    public JsonResult ok(T data) {
        return new JsonResult(0, "操作成功", data);
    }
}
