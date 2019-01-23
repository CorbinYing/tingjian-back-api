package org.corbin.common.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties({"result"})
public class ResponseResult <T> extends BaseResponse implements Serializable {
    private static final String DEFAULT_MSG="操作成功";


    private ResponseResult (Integer code){
        this.code=code;
    }

    private ResponseResult (Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }

    private ResponseResult (Integer code,String msg,T result){
        this.code=code;
        this.msg=msg;
        this.result=result;
    }

    private ResponseResult (Integer code,T result){
        this.code=code;
        this.msg=DEFAULT_MSG;
        this.result=result;
    }

    public static <T>ResponseResult<T> newInstance(Integer code){
        return new ResponseResult(code);
    }

    public static <T>ResponseResult<T> newInstance(Integer code,String msg){
        return new ResponseResult(code,msg);
    }

    public static <T>ResponseResult<T> newInstance(Integer code,String msg,T result){
        return new ResponseResult(code,msg,result);
    }

    public static <T>ResponseResult<T> newInstance(Integer code,T result){
        return new ResponseResult(code,DEFAULT_MSG,result);
    }

}
