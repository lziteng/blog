package com.lzt.ssm.blog.enums;

/**
 * @author lzt
 * @date 2020/1/10 14:38
 */
public enum ArticleStatus {

    PUBLISH(1, "发布"),
    DRAFT(0, "草稿");

    private Integer value;

    private String message;

    ArticleStatus(Integer value, String message) {
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
