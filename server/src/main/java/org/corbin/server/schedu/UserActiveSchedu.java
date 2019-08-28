package org.corbin.server.schedu;

import lombok.extern.slf4j.Slf4j;
import org.corbin.common.entity.UserActiveInfo;
import org.corbin.common.service.UserActiveInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserActiveSchedu {
    /**
     * 用户登录误操作在线时长
     * 单位：ms
     */
    //实验时为5min
    //private final static Long LOGIN_STATUS_NO_ACTION_TIME=30*60*1000L;
    private final static Long LOGIN_STATUS_NO_ACTION_TIME=5*60*1000L;

    private String pkq="       quu..__\n" +
            "         $$$b  `---.__\n" +
            "          \"$$b        `--.                          ___.---uuudP\n" +
            "           `$$b           `.__.------.__     __.---'      $$$$\"              .\n" +
            "             \"$b          -'            `-.-'            $$$\"              .'|\n" +
            "               \".                                       d$\"             _.'  |\n" +
            "                 `.   /                              ...\"             .'     |\n" +
            "                   `./                           ..::-'            _.'       |\n" +
            "                    /                         .:::-'            .-'         .'\n" +
            "                   :                          ::''\\          _.'            |\n" +
            "                  .' .-.             .-.           `.      .'               |\n" +
            "                  : /'$$|           .@\"$\\           `.   .'              _.-'\n" +
            "                 .'|$u$$|          |$$,$$|           |  <            _.-'\n" +
            "                 | `:$$:'          :$$$$$:           `.  `.       .-'\n" +
            "                 :                  `\"--'             |    `-.     \\\n" +
            "                :##.       ==             .###.       `.      `.    `\\\n" +
            "                |##:                      :###:        |        >     >\n" +
            "                |#'     `..'`..'          `###'        x:      /     /\n" +
            "                 \\                                   xXX|     /    ./\n" +
            "                  \\                                xXXX'|    /   ./\n" +
            "                  /`-.                                  `.  /   /\n" +
            "                 :    `-  ...........,                   | /  .'\n" +
            "                 |         ``:::::::'       .            |<    `.\n" +
            "                 |             ```          |           x| \\ `.:``.\n" +
            "                 |                         .'    /'   xXX|  `:`M`M':.\n" +
            "                 |    |                    ;    /:' xXXX'|  -'MMMMM:'\n" +
            "                 `.  .'                   :    /:'       |-'MMMM.-'\n" +
            "                  |  |                   .'   /'        .'MMM.-'\n" +
            "                  `'`'                   :  ,'          |MMM<\n" +
            "                    |                     `'            |tbap\\\n" +
            "                     \\                                  :MM.-'\n" +
            "                      \\                 |              .''\n" +
            "                       \\.               `.            /\n" +
            "                        /     .:::::::.. :           /\n" +
            "                       |     .:::::::::::`.         /\n" +
            "                       |   .:::------------\\       /\n" +
            "                      /   .''               >::'  /\n" +
            "                      `',:                 :    .'\n" +
            "                                           `:.:'\n" +
            "\n" ;


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
            //超时时间
            long overTime=(System.currentTimeMillis()-userActiveInfo.getUpdateTime().getTime()-LOGIN_STATUS_NO_ACTION_TIME);
            if (overTime>0){
                userActiveInfoService.deleteUserActive(userActiveInfo.getUserId());
                log.info(pkq);
                log.info("用户id为"+userActiveInfo.getUserId()+"已自动下线"+"超时时间:"+overTime);
            }
        }


    }

}
