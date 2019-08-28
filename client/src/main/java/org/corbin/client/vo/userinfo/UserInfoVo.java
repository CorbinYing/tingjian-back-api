package org.corbin.client.vo.userinfo;

import lombok.Getter;
import lombok.Setter;
import org.corbin.common.base.convert.BaseConvert;
import org.corbin.common.entity.UserInfo;
@Getter
@Setter
public class UserInfoVo extends BaseConvert<UserInfoVo, UserInfo> {

    protected String userId;

    private String userMail;

    private String userAccount;

    private String userTel;

    private String userName;

    private String userDesc;

    private Integer userSex;

    private String userHeadPortrait;

    public  UserInfoVo convert2Vo(UserInfo userInfo) {
        UserInfoVo vo= convert(userInfo, UserInfoVo.class);
        vo.setUserId(userInfo.getUserId().toString());
        return vo;
    }
}
