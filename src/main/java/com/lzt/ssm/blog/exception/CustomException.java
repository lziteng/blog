package com.lzt.ssm.blog.exception;

/**
 * 如果抛出此异常，系统会以json格式向前台返回异常信息
 * 使用方式： throw new CustomException("这里填入异常信息，会发送到前台");
 *
 * @author lzt
 * @date 2020/1/18 10:18
 */
public class CustomException extends  Exception{

    public String message;

    public CustomException(String message) {
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
