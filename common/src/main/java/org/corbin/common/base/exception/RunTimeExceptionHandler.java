package org.corbin.common.base.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.Response.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: RunTimeExceptionController
 * @Descripton: 异常处理控制器类
 */

@Slf4j
@RestControllerAdvice
@Data
public class RunTimeExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    public ResponseResult handleServiceException( ServiceException e){
        return e.getResponseResult();
    }

}
