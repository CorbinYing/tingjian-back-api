package org.corbin.server.schedu;

import lombok.extern.slf4j.Slf4j;
import org.corbin.common.entity.UserActiveInfo;
import org.corbin.server.service.UserActiveInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class UserActiveSchedu {
    /**
     * 用户登录误操作在线时长
     * 单位：min
     */
    private final static Integer LOGIN_STATUS_NO_ACTION_TIME=30;

    @Autowired
    private UserActiveInfoService userActiveInfoService;

    /**
     * 30s检查一次
     * 用户的状态，超过30min自动下线
     */

    @Scheduled(cron = "0/30 * * * * ?")
    @Async("myThreadPoolTaskExcuror")
    void checkUserActiveStatus() {

        List<UserActiveInfo> userActiveInfoList = userActiveInfoService.findAll();
        if (userActiveInfoList == null) {
            return;
        }
        log.info("执行用户自动下线策略");
        //用户操作
        for (UserActiveInfo userActiveInfo : userActiveInfoList) {
            if ((new Date()).getTime()-userActiveInfo.getUpdateTime().getTime()-LOGIN_STATUS_NO_ACTION_TIME*1000>0){
                userActiveInfoService.deleteUserActive(userActiveInfo.getUserId());
                log.info("用户id为"+userActiveInfo.getUserId()+"已自动下线");
            }
        }


    }

}
