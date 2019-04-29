package com.springboot.demo.common;

public class ReturnString {

    /**
     * 0：成功 -1：失败
     */
    private int code = 0;

    private String message = "数据获取成功";

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ReturnString(Object data) {
        this.data = data;
    }

    public ReturnString(String message) {
        this.code = -1;
        this.message = message;
    }

    public ReturnString(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
