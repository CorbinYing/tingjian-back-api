package org.corbin.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.corbin.client.service.UserInfoService;
import org.corbin.client.vo.userinfo.UserLoginVo;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.Response.ResponseResult;
import org.corbin.common.base.controller.BaseController;
import org.corbin.common.base.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;

    @PostMapping
    public ResponseResult login(@Valid @RequestBody UserLoginVo vo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ServiceException(ResponseCode.ERR_10001, bindingResult.getFieldError().getDefaultMessage());
        }

        boolean isAllowLogin=userInfoService.isAllowLogin(vo.getUserAccount(),vo.getUserPwd());
        if (!isAllowLogin){
            throw new ServiceException()
        }


        return null;
    }


}
