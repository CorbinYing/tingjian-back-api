package org.corbin.client.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.CollectInfo;
import org.corbin.common.entity.SongInfo;
import org.corbin.common.entity.SongStatisticsDayLog;
import org.corbin.common.repository.SongInfoRepository;
import org.corbin.common.repository.SongStatisticsDayLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SongInfoService extends BaseService {
    @Autowired
    private CollectInfoService collectInfoService;
    @Autowired
    private SongInfoRepository songInfoRepository;
    @Autowired
    private SongStatisticsDayLogRepository songStatisticsDayLogRepository;

    /**
     * 今日推荐歌曲
     *
     * @param pageable
     * @return
     */
    public Page<SongInfo> songListRecommendToday(Pageable pageable) {
        List<SongInfo> songList = Lists.newArrayList();

        Page<SongStatisticsDayLog> songStatisticsDayLogPage = songStatisticsDayLogRepository.findAllByOrderByRecommendPointDesc(pageable);

        if (songStatisticsDayLogPage == null || songStatisticsDayLogPage.getTotalElements() <= 0) {
            return null;
        }

        //find songs
        for (SongStatisticsDayLog statisticsDayLog : songStatisticsDayLogPage.getContent()) {
            SongInfo songInfo = songInfoRepository.findBySongId(statisticsDayLog.getSongId());
            songList.add(songInfo);
        }

        return new PageImpl<>(songList, pageable, songList.size());

    }

    /**
     * 热门神曲
     *
     * @param pageable
     * @return
     */
    public Page<SongInfo> songListHotToday(Pageable pageable) {
        List<SongInfo> songList = Lists.newArrayList();
        Page<SongStatisticsDayLog> songStatisticsDayLogPage = songStatisticsDayLogRepository.findAllByOrderByHotPointDesc(pageable);

        if (songStatisticsDayLogPage == null || songStatisticsDayLogPage.getTotalElements() <= 0) {
            return null;
        }
        //find songs
        for (SongStatisticsDayLog songStatisticsDayLog : songStatisticsDayLogPage.getContent()) {
            SongInfo songInfo = songInfoRepository.findBySongId(songStatisticsDayLog.getSongId());
            songList.add(songInfo);
        }

        return new PageImpl<>(songList, pageable, songList.size());
    }


    /**
     * 获取最新30天歌曲信息
     *
     * @param pageable
     * @return
     */
    public Page<SongInfo> getLast30DaysSongList(Pageable pageable) {
        Date date = new Date(System.currentTimeMillis() - 30 * 24 * 60 * 60 * 1000);
        return songInfoRepository.findAllBySongShelfTimeAfterAndOrderByCreateTime(date, pageable);
    }


    /**
     * 查找用户收藏的音乐
     *
     * @param userId
     * @param pageable
     * @return
     */
    public Page<SongInfo> getCollectSongPage(Long userId, Pageable pageable) {
        List<SongInfo> songInfoList = Lists.newArrayList();
        Page<CollectInfo> collectInfoPage = collectInfoService.findCollectSong(userId);

        if (collectInfoPage == null) {
            return null;
        }

        for (CollectInfo collectInfo : collectInfoPage.getContent()) {
            SongInfo songInfo = songInfoRepository.findBySongId(collectInfo.getCollectId());
            songInfoList.add(songInfo);
        }

        Page<SongInfo> page = pageImpl(songInfoList, pageable);
        return page;

    }
}
