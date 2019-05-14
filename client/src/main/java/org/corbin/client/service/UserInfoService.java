package org.corbin.client.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.tomcat.util.security.MD5Encoder;
import org.corbin.client.repository.*;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.exception.ServiceException;
import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.UserInfo;
import org.corbin.common.util.IdHelper;
import org.corbin.common.util.InitializeUtil;
import org.corbin.common.util.MD5Util;
import org.corbin.common.util.PatternUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.security.MessageDigest;


/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: UserInfoService
 * @Descripton:
 */
@Service
@Slf4j
public class UserInfoService extends BaseService {
    @Autowired
    private CollectInfoRepository collectInfoRepository;
    @Autowired
    private CommentInfoRepository commentInfoRepository;
    @Autowired
    private SingerInfoRepository singerInfoRepository;
    @Autowired
    private SongInfoRepository songInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Autowired
    private UserActiveInfoService userActiveInfoService;

    public UserInfo findOne(UserInfo user) {
        return userInfoRepository.findOne(Example.of(user)).orElse(null);
    }


    /**
     * @Author: Corbin
     * @Date: 2019/2/8
     * @Param: [account, pwd]
     * @return: boolean
     * @Description: 是否允许登录
     */
    public UserInfo userLogin(@NonNull String accountOrMail, String pwd) {
        UserInfo userInfo = InitializeUtil.EMPTY();

        boolean isMail = PatternUtil.isMail(accountOrMail);
        if (isMail) {
            userInfo = userInfoRepository.findByUserMail(accountOrMail);
        } else {
            userInfo = userInfoRepository.findByUserAccount(accountOrMail);
        }


        if (userInfo == null) {
            //账号不存在
            throw new ServiceException(ResponseCode.ERR_11001);
        }

        boolean flag = MD5Util.string2MD5(pwd).equals(userInfo.getUserPwd());
        if (!flag) {
            //密码不正确
            throw new ServiceException(ResponseCode.ERR_11002);
        }

        //保存登录状态
        userActiveInfoService.updateUserActive(userInfo.getUserId());

        return userInfo;
    }

    /**
     * register user
     *
     * @param userInfo
     * @return
     */
    public UserInfo createUser(UserInfo userInfo) {

        Assert.notNull(userInfo.getUserMail(), "mail must not be null");

        userInfo.setUserId(IdHelper.snowflake.nextId2Long());
        UserInfo user = userInfoRepository.findByUserMail(userInfo.getUserMail());
        if (user != null) {
            //用户已存在
            throw new ServiceException(ResponseCode.ERR_13001);
        }
        userInfo.setUserAccount(userInfo.getUserMail());
        userInfo.setUserPwd(MD5Util.string2MD5(userInfo.getUserPwd()));
        return userInfoRepository.saveAndFlush(userInfo);
    }

    public UserInfo findByUserId(Long userId) {
        return userInfoRepository.findByUserId(userId);
    }

    /**
     * 编辑用户的信息
     * @param userInfo
     * @return
     */
    public UserInfo editUserInfo(UserInfo userInfo){
        userInfo=userInfoRepository.saveAndFlush(userInfo);
        return userInfo;
    }

}
