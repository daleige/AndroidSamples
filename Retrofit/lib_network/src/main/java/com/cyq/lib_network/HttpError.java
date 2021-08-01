package com.cyq.lib_network;

/**
 * @author chenyq113@midea.com
 * @describe 错误信息类
 * @time 2021/2/1 14:59
 */
public class HttpError {
    private int code;
    private String message;

    public HttpError(int code, String message) {
        this.code = code;
        this.message = message;
    }

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

    @Override
    public String toString() {
        return "HttpError{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
