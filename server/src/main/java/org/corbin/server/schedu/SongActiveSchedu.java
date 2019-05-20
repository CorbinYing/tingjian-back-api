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
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Slf4j
public class SongActiveSchedu {
//    @Autowired
//    @Qualifier("createThreadPoolTaskExcuror")
//    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private SongStatisticsDayLogRepository songStatisticsDayLogRepository;
    @Autowired
    private SongInfoService songInfoService;
    @Autowired
    private SongInfoRepository songInfoRepository;

    /**
     * 凌晨更新，播放次数，收藏次数，点赞次数
     */

  //  @Scheduled(cron = "0 0 0 * * ?")
//    @Scheduled(cron = "0 0/1 * * * ? ")
//    @Async("myThreadPoolTaskExcuror")
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
     * 定时更新歌曲的热度
     */
//    @Scheduled(cron = "0 */5 * * * ? ")
//    @Scheduled(cron = "0/5 * * * * ? ")
//    @Async("myThreadPoolTaskExcuror")
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
   // @Scheduled(cron = "0 */10 * * * ? ")
 //   @Scheduled(cron = "0/10 * * * * ? ")
//    @Async("myThreadPoolTaskExcuror")
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


//    @Scheduled(cron = "0/3 * * * * ? ")
//    @Async("myThreadPoolTaskExcuror")
    void lie() {
        List<SongInfo>songInfoList=songInfoRepository.findAllNeedAddIntoLog();
        List<SongStatisticsDayLog> logList=Lists.newArrayList();
        //添加没有的歌曲
        if (songInfoList!=null){
            for (SongInfo songInfo:songInfoList){
                SongStatisticsDayLog log=new SongStatisticsDayLog();
                log.setSongId(songInfo.getSongId());
                logList.add(log);
            }
            songStatisticsDayLogRepository.saveAll(logList);
        }

        //---度处理

        List<SongStatisticsDayLog> songStatisticsDayLogList=songStatisticsDayLogRepository.findAll();
        for (int i=0;i<100;i++){
            Random random=new Random();
           int index= random.nextInt(songStatisticsDayLogList.size());
           songStatisticsDayLogList.get(index).setStarTimesToday(random.nextInt(100));
            songStatisticsDayLogList.get(index).setPlayTimesToday(random.nextInt(1000));
            songStatisticsDayLogList.get(index).setCollectTimesToday(random.nextInt(100));

        }
        songStatisticsDayLogRepository.saveAll(songStatisticsDayLogList);







    }

}
