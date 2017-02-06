package com.ifoji.common.dal.model;

/**
 * Created by qsq
 */
public class KFQBaseModel<T> implements BaseModel<T> {

    public int code;

    public T data;

    public String message;

    @Override
    public T getData() {
        return data;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
