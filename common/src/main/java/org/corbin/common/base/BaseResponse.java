package org.corbin.common.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public abstract  class BaseResponse<T> implements Serializable {

    protected Integer code;
    protected String  msg;
    protected T result;

}
