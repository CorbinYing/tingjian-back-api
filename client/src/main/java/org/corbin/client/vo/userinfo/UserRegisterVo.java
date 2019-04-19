package org.corbin.client.vo.userinfo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.convert.BaseConvert;
import org.corbin.common.entity.UserInfo;

import javax.validation.constraints.NotBlank;

@Data
@Slf4j
public class UserRegisterVo extends BaseConvert<UserRegisterVo, UserInfo> {

    @NotBlank(message = "邮箱不能为空")
    private String userMail;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String userPwd;
}
