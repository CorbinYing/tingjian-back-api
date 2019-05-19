package org.corbin.client.controller;

import lombok.extern.slf4j.Slf4j;
import org.corbin.client.base.controller.BaseClientController;
import org.corbin.client.service.UserActiveInfoService;
import org.corbin.client.service.UserInfoService;
import org.corbin.client.service.VerificationLogService;
import org.corbin.client.vo.userinfo.UserInfoVo;
import org.corbin.client.vo.userinfo.UserLoginVo;
import org.corbin.client.vo.userinfo.UserRegisterVo;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.Response.ResponseResult;
import org.corbin.common.base.exception.ServiceException;
import org.corbin.common.base.vo.BaseVo;
import org.corbin.common.entity.UserInfo;
import org.corbin.common.util.PatternUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserInfoController extends BaseClientController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    protected VerificationLogService verificationLogService;
    @Autowired
    private UserActiveInfoService userActiveInfoService;

    @PostMapping("/login")
    public ResponseResult login(@Valid @RequestBody UserLoginVo vo, BindingResult bindingResult) {
        bindingResultVarify(bindingResult);

        //验证登录
        UserInfo userInfo = userInfoService.userLogin(vo.getAccount(), vo.getUserPwd());
        return ResponseResult.newInstance(ResponseCode.SUCC_0, (new UserInfoVo()).convert2Vo(userInfo));
    }


    /**
     * send verification_code to the mail
     *
     * @param mail
     * @return
     */
    @PostMapping("/verify-code")
    public ResponseResult sendVerificationCode(@RequestParam("userMail") String mail) {
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

        UserInfo newUser = vo.counvert2Entity(vo, UserInfo.class);
        String registerCode = vo.getRegisterCode();
        //check registerCode
        verificationLogService.checkVerificationCode(registerCode, vo.getUserMail());
        userInfoService.createUser(newUser);

        return ResponseResult.newInstance(ResponseCode.SUCC_0);

    }

    /**
     * 验证用户是否登录
     * 只需要useId即可
     *
     * @param vo
     * @return
     */
    @PostMapping("/check/user/login-status")
    public ResponseResult checkUserLoginStatus(@RequestBody BaseVo vo) {
        isUserLogin(vo);
        return ResponseResult.newInstance(ResponseCode.SUCC_0);
    }


    /**
     * 退出登录
     *
     * @param vo
     * @return
     */
    @PostMapping("/logout")
    public ResponseResult logout(@RequestBody BaseVo vo) {
        isUserLogin(vo);
        userActiveInfoService.deleteUserActive(vo.getUserId());
        return ResponseResult.newInstance(ResponseCode.SUCC_0);

    }

    /**
     * 编辑用户信息
     * @param vo
     * @return
     */
    @PostMapping("/edit/user-info")
    public ResponseResult logout(@RequestBody  UserInfoVo vo) {
        Long userId=Long.valueOf(vo.getUserId());
        isUserLogin(userId);

       UserInfo userInfo= userInfoService.findByUserId(userId);
       if (userInfo==null){
           throw new ServiceException(ResponseCode.ERR_11001);
       }

       userInfo.setUserDesc(vo.getUserDesc());
       userInfo.setUserName(vo.getUserName());

       userInfoService.editUserInfo(userInfo);

        return ResponseResult.newInstance(ResponseCode.SUCC_0,(new UserInfoVo()).convert2Vo(userInfo));

    }

}
