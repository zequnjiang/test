package com.ifoji.common.dal.model;

/**
 * 默认 Model 类型
 * Created by qsq on
 */
public interface BaseModel<T> {

    T getData();

    int getCode();

    String getMessage();

}
