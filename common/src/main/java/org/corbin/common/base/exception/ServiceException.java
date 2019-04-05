package org.corbin.common.base.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.Response.ResponseResult;

import java.io.Serializable;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: ServiceException
 * @Descripton: 自定义异常
 */

@Slf4j
@Data
public class ServiceException extends RuntimeException implements Serializable {

    private ResponseResult responseResult;
    private Integer errCode;
    private String errMsg;

    public ServiceException(Integer errCode,String errMsg){
        this.errCode=errCode;
        this.errMsg=errMsg;
        responseResult=ResponseResult.newInstance(errCode,errMsg);
    }

    public ServiceException(Integer errCode){
        this.errCode=errCode;
        responseResult=ResponseResult.newInstance(errCode);
    }
}
