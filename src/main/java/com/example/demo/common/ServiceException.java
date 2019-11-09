package com.example.demo.common;

/**
 * @Author: YafengLiang
 * @Description:
 * @Date: Created in  11:11 2018/5/31
 */
public class ServiceException extends RuntimeException {
    private int code;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ServiceException(String message, int code) {
        super(message);
        this.code = code;
    }

    public ServiceException(String message) {
        super(message);
    }
}
