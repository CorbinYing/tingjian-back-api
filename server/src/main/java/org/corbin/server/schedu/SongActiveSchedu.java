package org.corbin.server.schedu;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.corbin.common.entity.SongInfo;
import org.corbin.common.entity.SongStatisticsDayLog;
import org.corbin.server.repository.SongInfoRepository;
import org.corbin.server.repository.SongStatisticsDayLogRepository;
import org.corbin.server.service.SongInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class SongActiveSchedu {
    @Autowired
    private SongStatisticsDayLogRepository songStatisticsDayLogRepository;
    @Autowired
    private SongInfoService songInfoService;
    @Autowired
    private SongInfoRepository songInfoRepository;

    /**
     * 每日更新
     * 凌晨更新，播放次数，收藏次数，点赞次数
     */

    //实验时，模拟2分钟为1天
    //  @Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(cron = "0 0/2 * * * ? ")
    @Async("myThreadPoolTaskExcuror")
    void updateSongStatisticsLog() {
        List<SongStatisticsDayLog> list = songStatisticsDayLogRepository.findAll();
        if (list == null) {
            return;
        }
        for (SongStatisticsDayLog songStatisticsDayLog : list
        ) {
            songStatisticsDayLog.setCollectTimesYesterday(songStatisticsDayLog.getCollectTimesToday());
            songStatisticsDayLog.setCollectTimesToday(0);

            songStatisticsDayLog.setPlayTimesYesterday(songStatisticsDayLog.getPlayTimesToday());
            songStatisticsDayLog.setPlayTimesToday(0);

            songStatisticsDayLog.setStarTimesYesterday(songStatisticsDayLog.getStarTimesToday());
            songStatisticsDayLog.setStarTimesToday(0);
        }

        songStatisticsDayLogRepository.saveAll(list);

    }

    /**
     * 定时更新歌曲的热度,20分钟更新一次
     */
//    @Scheduled(cron = "0 0/20 * * * ? ")
    //实验时，30s更新一次
    @Scheduled(cron = "0/20 * * * * ? ")
    @Async("myThreadPoolTaskExcuror")
    void updateSongHotStatus() {
        List<SongStatisticsDayLog> list = songStatisticsDayLogRepository.findAll();
        for (SongStatisticsDayLog songStatisticsDayLog : list
        ) {
            songStatisticsDayLog.setHotPoint(point(songStatisticsDayLog));
        }
        songStatisticsDayLogRepository.saveAll(list);
    }


    /**
     * 定时更新歌曲的推荐度,
     * 收藏：点赞：播放次数=4：4：2
     * 昨日：今日=4:6
     * 额外的新歌：旧歌=6:4
     * 即4:4:2:6:6:3   新歌×0.6  旧歌×0.4
     * 0.16:0.16:0.8:0.24:0.24:0.12
     */
    // @Scheduled(cron = "0 10/20 * * * ? ")
    //实验时模拟20s更新一次
    @Scheduled(cron = "10/20 * * * * ? ")
    @Async("myThreadPoolTaskExcuror")
    void updateSongRecommendStatus() {

        List<SongStatisticsDayLog> list = songStatisticsDayLogRepository.findAll();
        List<SongInfo> newSongList = songInfoService.getLast30DaysSongList();
        List<Long> newSongIdList = Lists.newArrayList();

        if (newSongList == null) {
            return;
        }
        //获取新歌id列表
        for (int i = 0; i < newSongList.size(); i++) {
            newSongIdList.add(newSongList.get(i).getSongId());
        }

        //提高新歌优先级
        for (SongStatisticsDayLog songStatisticsDayLog : list
        ) {
            if (newSongIdList.contains(songStatisticsDayLog.getSongId())) {
                songStatisticsDayLog.setRecommendPoint(0.6 * point(songStatisticsDayLog));
            } else {
                songStatisticsDayLog.setRecommendPoint(0.4 * point(songStatisticsDayLog));
            }
        }
        songStatisticsDayLogRepository.saveAll(list);
    }

    /**
     * 热度点数
     * <p>
     * * 定时更新歌曲的推荐度,
     * * 收藏：点赞：播放次数=4：4：2
     * * 昨日：今日=4:6
     * * 额外的新歌：旧歌=6:4
     * * 即4:4:2:6:6:3   新歌×0.6  旧歌×0.4
     * * 0.16:0.16:0.08:0.24:0.24:0.12
     */
    Double point(@NonNull SongStatisticsDayLog songStatisticsDayLog) {
        int collect_y = songStatisticsDayLog.getCollectTimesYesterday();
        int collect_t = songStatisticsDayLog.getCollectTimesToday();

        int star_y = songStatisticsDayLog.getStarTimesYesterday();
        int stat_t = songStatisticsDayLog.getStarTimesToday();

        int play_y = songStatisticsDayLog.getPlayTimesYesterday();
        int play_t = songStatisticsDayLog.getPlayTimesToday();

        double point = collect_y * 0.16 + star_y * 0.16 + play_y * 0.08 + collect_t * 0.24 + stat_t * 0.24 + play_t * 0.12;

        return point;

    }


    /**
     * 作弊程序
     * 模拟用户进行点赞、收藏、播放歌曲，
     * 模拟大数据下的用户操作，采集用户行为数据，用于计算歌曲的热度、推荐度
     */
    @Scheduled(cron = "5/10 * * * * ? ")
    @Async("myThreadPoolTaskExcuror")
    void lie() {
        List<SongInfo> songInfoList = songInfoRepository.findAllNeedAddIntoLog();
        List<SongStatisticsDayLog> logList = Lists.newArrayList();
        //添加日统计表中没有的歌曲
        if (songInfoList != null) {
            for (SongInfo songInfo : songInfoList) {
                SongStatisticsDayLog log = new SongStatisticsDayLog();
                log.setSongId(songInfo.getSongId());
                logList.add(log);
            }
            songStatisticsDayLogRepository.saveAll(logList);
        }

        //-模拟用户操作，进行度处理
        //每次选取100首歌，随机增加点赞、收藏、播放次数
        List<SongStatisticsDayLog> songStatisticsDayLogList = songStatisticsDayLogRepository.findAll();
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int index = random.nextInt(songStatisticsDayLogList.size());
            songStatisticsDayLogList.get(index).setStarTimesToday(random.nextInt(100));
            songStatisticsDayLogList.get(index).setPlayTimesToday(random.nextInt(1000));
            songStatisticsDayLogList.get(index).setCollectTimesToday(random.nextInt(100));

        }
        songStatisticsDayLogRepository.saveAll(songStatisticsDayLogList);


    }

}
