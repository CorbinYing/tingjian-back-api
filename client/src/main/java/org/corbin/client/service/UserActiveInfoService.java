package org.corbin.client.service;

import lombok.extern.slf4j.Slf4j;
import org.corbin.client.repository.UserInfoRepository;
import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.UserActiveInfo;
import org.corbin.client.repository.UserActiveInfoRepository;
import org.corbin.common.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserActiveInfoService extends BaseService {
    @Autowired
    private UserActiveInfoRepository userActiveInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    /**
     * 添加或更新用户记录
     *
     * @param userActiveInfo
     * @return
     */
    public UserActiveInfo updateUserActive(@NonNull UserActiveInfo userActiveInfo) {
        if (userActiveInfo.getUserId() == null) {
            log.error("用户id不可为空");
            return null;
        }
        return userActiveInfoRepository.saveAndFlush(userActiveInfo);
    }


    /**
     *  insert user acyive
     * @param userId
     * @return
     */
    public UserActiveInfo updateUserActive(@NonNull Long userId) {
        boolean isUserLogind = isUserLogin(userId);
        UserActiveInfo userActiveInfo = null;

        if (isUserLogind) {

            userActiveInfo = findByUserId(userId);
            userActiveInfo.setUpdateTime(new Date());
            updateUserActive(userActiveInfo);

        } else {
            UserInfo userInfo = userInfoRepository.findByUserId(userId);

            userActiveInfo = new UserActiveInfo();
            userActiveInfo.setUserId(userInfo.getUserId());
            updateUserActive(userActiveInfo);
        }
        return userActiveInfo;
    }


    /**
     * 根据userid 删除用户的登录记录
     * 此部分由定时任务做
     *
     * @param userId
     */
    public void deleteUserActive(@NonNull Long userId) {
        userActiveInfoRepository.deleteByUserId(userId);
    }

    public List<UserActiveInfo> findAll() {
        return userActiveInfoRepository.findAll();
    }


    public UserActiveInfo findByUserId(Long userId) {
        return userActiveInfoRepository.findByUserId(userId);
    }

    /**
     * 用户是否在线
     *
     * @param userId
     * @return
     */
    public boolean isUserLogin(Long userId) {
        if (userId == null || 0 == userId) {
            return false;
        }
        UserActiveInfo userActiveInfo = findByUserId(userId);
        return userActiveInfo == null ? false : true;
    }

}
