package org.corbin.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.corbin.client.service.UserInfoService;
import org.corbin.client.service.VerificationLogService;
import org.corbin.client.vo.userinfo.UserLoginVo;
import org.corbin.client.vo.userinfo.UserRegisterVo;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.Response.ResponseResult;
import org.corbin.common.base.controller.BaseController;
import org.corbin.common.entity.UserInfo;
import org.corbin.common.util.PatternUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    protected VerificationLogService verificationLogService;

    @PostMapping("/login")
    public ResponseResult login(@Valid @RequestBody UserLoginVo vo, BindingResult bindingResult) {
        bindingResultVarify(bindingResult);

        //验证登录
        userInfoService.userLogin(vo.getAccount(), vo.getUserPwd());

        return ResponseResult.newInstance(ResponseCode.SUCC_0);
    }


    /**
     * send verification_code to the mail
     *
     * @param mail
     * @return
     */
    @PostMapping("/verify-code")
    public ResponseResult sendVerificationCode(@RequestParam String mail) {
        //is not a mail
        if (!PatternUtil.isMail(mail)) {
            return ResponseResult.newInstance(ResponseCode.ERR_10002);
        }

        // send verification code
        verificationLogService.sendVerificationCode(mail);

        return ResponseResult.newInstance(ResponseCode.SUCC_0);
    }

    @PostMapping("/register")
    public ResponseResult register(@Valid @RequestBody UserRegisterVo vo, BindingResult bindingResult) {
        bindingResultVarify(bindingResult);

        UserInfo newUser=vo.counvert2Entity(vo,UserInfo.class);
        userInfoService.createUser(newUser);

        return ResponseResult.newInstance(ResponseCode.SUCC_0,newUser);

    }

}
