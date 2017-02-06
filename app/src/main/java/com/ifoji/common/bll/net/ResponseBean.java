package com.ifoji.common.bll.net;


import com.google.gson.JsonObject;

/**
 * Created by xiaoze on 16/8/25.
 */
public class ResponseBean {
    private int code;
    private String message;
    private JsonObject data;

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

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
