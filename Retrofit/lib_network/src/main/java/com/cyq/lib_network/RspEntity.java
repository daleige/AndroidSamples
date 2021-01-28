package com.cyq.lib_network;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/1/28 17:30
 */
public class RspEntity<T> {
    private int code;

    private String description;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RspEntity{" +
                "code=" + code +
                ", description='" + description + '\'' +
                ", data=" + data +
                '}';
    }
}
