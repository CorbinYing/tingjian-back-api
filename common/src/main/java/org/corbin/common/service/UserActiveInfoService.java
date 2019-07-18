package org.corbin.common.service;

import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.UserActiveInfo;
import org.corbin.common.entity.UserInfo;
import org.corbin.common.repository.UserActiveInfoRepository;
import org.corbin.common.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Slf4j
public class UserActiveInfoService extends BaseService<UserActiveInfo,Long> {
    @Autowired
    private UserActiveInfoRepository userActiveInfoRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;



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



//    public List<UserActiveInfo> findAll() {
//        return userActiveInfoRepository.findAll();
//    }


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
     * 根据userid 删除用户的登录记录
     * 此部分由定时任务做
     * @param userId
     */
    @Transactional
    public void deleteUserActive(@NonNull Long userId){
        userActiveInfoRepository.deleteByUserId(userId);
    }

}
