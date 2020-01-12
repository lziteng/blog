package com.lzt.ssm.blog.enums;

/**
 * @Author: lzt
 * @Date: 2020/1/12 11:48
 */
public enum UserStatus {

    NORMAL(1, "正常"),
    FORBIDDEN(0, "禁用");

    private Integer value;

    private String message;

    UserStatus(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
