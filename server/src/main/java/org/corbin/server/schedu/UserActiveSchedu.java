package org.corbin.server.schedu;

import org.corbin.common.repository.UserActiveInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class UserActiveSchedu {
    @Autowired
    private UserActiveInfoRepository userActiveInfoRepository;

    /**
     * 定时检查用户的状态，超过30min自动下线
     */

    @Scheduled(cron = "* 0/1 * * * * ?")
    void checkUserActiveStatus() {
      //  List<UserActiveInfo>
    }

}
