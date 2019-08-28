package org.corbin.common.base.controller;

import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.exception.RunTimeExceptionHandler;
import org.corbin.common.base.exception.ServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;


/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: BaseController
 * @Descripton:
 */

@Slf4j
@RestController
public class BaseController extends RunTimeExceptionHandler {

    public void bindingResultVarify(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ServiceException(ResponseCode.ERR_10001, bindingResult.getFieldError().getDefaultMessage());
        }
    }

    public File createFile(String fileNameWithPath) {
        File localFile = new File(fileNameWithPath);

        if (localFile.getParentFile() != null && !localFile.getParentFile().exists()) {
            localFile.getParentFile().mkdirs();
        }
        try {
            localFile.createNewFile();
        } catch (IOException e) {
            log.error(null, e);
        }
        return localFile;
    }


}
