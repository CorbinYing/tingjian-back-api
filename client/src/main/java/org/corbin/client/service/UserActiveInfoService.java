package org.corbin.client.service;

import lombok.extern.slf4j.Slf4j;
import org.corbin.common.repository.UserActiveInfoRepository;
import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.UserActiveInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserActiveInfoService extends BaseService {
    @Autowired
    private UserActiveInfoRepository userActiveInfoRepository;

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
    public void deleteUserActive(@NonNull Long userId){
         userActiveInfoRepository.deleteById(userId);
    }
}
