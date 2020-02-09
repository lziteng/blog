package com.lzt.ssm.blog.exception;

/**
 * 此异常类型用来向前台返回页面和信息
 *
 * @author lzt
 * @date 2020/1/18 10:20
 */
public class ReturnViewException extends Exception {

    public String message;

    public ReturnViewException() {
    }

    public ReturnViewException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
