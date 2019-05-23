package org.corbin.client.service;

import org.corbin.client.repository.*;
import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.SongStatisticsDayLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class SongStatisticsDayLogService extends BaseService {
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
    private SongStatisticsDayLogRepository songStatisticsDayLogRepository;


    public SongStatisticsDayLog updateSongStatisicsDayLog(@NonNull SongStatisticsDayLog songStatisticsDayLog) {
        return songStatisticsDayLogRepository.saveAndFlush(songStatisticsDayLog);
    }

    /**
     * 更新点赞次数
     * @param songId
     * @return
     */
    public SongStatisticsDayLog updateStarNum(@NonNull Long songId){
        //记录今天的点赞数
        SongStatisticsDayLog songStatisticsDayLog= songStatisticsDayLogRepository.findBySongId(songId);
        if (songStatisticsDayLog==null){
            songStatisticsDayLog=new SongStatisticsDayLog();
            songStatisticsDayLog.setSongId(songId);
        }

        Integer todayStartTimes=songStatisticsDayLog.getStarTimesToday();
        todayStartTimes=todayStartTimes==null?0:todayStartTimes;
        songStatisticsDayLog.setStarTimesToday(todayStartTimes+1);
        songStatisticsDayLogRepository.save(songStatisticsDayLog);
        return songStatisticsDayLog;

    }

    /**
     * 更新今日收藏数
     * @param songId
     * @return
     */
    public SongStatisticsDayLog updateCollectNum(@NonNull Long songId){
        //记录今天的收藏数
        SongStatisticsDayLog songStatisticsDayLog= songStatisticsDayLogRepository.findBySongId(songId);
        if (songStatisticsDayLog==null){
            songStatisticsDayLog=new SongStatisticsDayLog();
            songStatisticsDayLog.setSongId(songId);
        }

        Integer todayCollectTimes=songStatisticsDayLog.getCollectTimesToday();
        todayCollectTimes=todayCollectTimes==null?0:todayCollectTimes;
        songStatisticsDayLog.setCollectTimesToday(todayCollectTimes+1);
        songStatisticsDayLogRepository.save(songStatisticsDayLog);

        return songStatisticsDayLog;
    }

    /**
     * 更新播放次数
     * @param songId
     * @return
     */
    public SongStatisticsDayLog updatePlayNum(@NonNull Long songId){
        //记录今天的播放数
        SongStatisticsDayLog songStatisticsDayLog= songStatisticsDayLogRepository.findBySongId(songId);
        if (songStatisticsDayLog==null){
            songStatisticsDayLog=new SongStatisticsDayLog();
            songStatisticsDayLog.setSongId(songId);
        }

        Integer todayPlayTimes=songStatisticsDayLog.getPlayTimesToday();
        todayPlayTimes=todayPlayTimes==null?0:todayPlayTimes;
        songStatisticsDayLog.setPlayTimesToday(todayPlayTimes+1);
        songStatisticsDayLogRepository.save(songStatisticsDayLog);

        return  songStatisticsDayLog;
    }
}
