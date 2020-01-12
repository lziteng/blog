package com.lzt.ssm.blog.enums;

/**
 * @Author: lzt
 * @Date: 2020/1/12 22:27
 */
public enum UserType {

    IN(1, "内网用户"),
    OUT(0, "外网用户");

    private Integer value;

    private String message;

    UserType(Integer value, String message) {
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
