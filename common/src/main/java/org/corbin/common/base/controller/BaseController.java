package org.corbin.common.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.exception.RunTimeExceptionHandler;
import org.corbin.common.base.exception.ServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: BaseController
 * @Descripton:
 */

@Slf4j
@RestController
public class BaseController extends RunTimeExceptionHandler {

    public void bindingResultVarify(BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new ServiceException(ResponseCode.ERR_10001, bindingResult.getFieldError().getDefaultMessage());
        }
    }


}
