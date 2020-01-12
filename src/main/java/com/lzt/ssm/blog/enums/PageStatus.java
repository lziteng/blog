package com.lzt.ssm.blog.enums;

/**
 * @Author: lzt
 * @Date: 2020/1/11 14:57
 */
public enum PageStatus {

    /**
     * 定制的页面，需要维护相应的访问地址和页面(在数据库中手动添加此类数据)
     */
    CUSTOMIZE(2, "系统定制"),
    NORMAL(1, "显示"),
    HIDDEN(0, "隐藏");

    private Integer value;

    private String message;

    PageStatus(Integer value, String message) {
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
