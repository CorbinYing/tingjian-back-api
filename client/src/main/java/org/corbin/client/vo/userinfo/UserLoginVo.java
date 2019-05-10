package org.corbin.client.vo.userinfo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.convert.BaseConvert;
import org.corbin.common.entity.UserInfo;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: UserLoginVo
 * @Descripton:
 */
@Data
@Slf4j
public class UserLoginVo extends BaseConvert<UserLoginVo, UserInfo> {
    @NotBlank(message = "账户名不能为空")
    private String account;

    @NotBlank(message = "密码不能为空")
    private String userPwd;




}
