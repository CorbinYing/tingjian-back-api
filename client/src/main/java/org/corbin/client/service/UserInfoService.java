package org.corbin.client.service;

import lombok.extern.slf4j.Slf4j;
import org.corbin.client.repository.*;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.exception.ServiceException;
import org.corbin.common.entity.UserInfo;
import org.corbin.common.util.InitializeUtil;
import org.corbin.common.util.PatternUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;


/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: UserInfoService
 * @Descripton:
 */
@Service
@Slf4j
public class UserInfoService {
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
    public void userLogin(@NonNull String accountOrMail, String pwd) {
        UserInfo userInfo = InitializeUtil.EMPTY();

        boolean isMail = PatternUtil.isMail(accountOrMail);
        if (isMail) {
            userInfo = userInfoRepository.findByUserMail(accountOrMail);
        } else {
            userInfo = userInfoRepository.findByUserAccount(accountOrMail);
        }


        if (userInfo == null) {
            throw new ServiceException(ResponseCode.ERR_11001);
        }
        boolean flag = pwd.equals(userInfo.getUserPwd());
        if (!flag) {
            throw new ServiceException(ResponseCode.ERR_11002);
        }

    }

    public UserInfo createUser(UserInfo userInfo) {
        return userInfoRepository.saveAndFlush(userInfo);
    }


}
