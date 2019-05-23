package org.corbin.client.service;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.corbin.client.repository.CollectInfoRepository;
import org.corbin.client.repository.SongInfoRepository;
import org.corbin.client.repository.SongStatisticsDayLogRepository;
import org.corbin.common.base.Response.ResponseCode;
import org.corbin.common.base.exception.ServiceException;
import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.CollectInfo;
import org.corbin.common.entity.SingerInfo;
import org.corbin.common.entity.SongInfo;
import org.corbin.common.entity.SongStatisticsDayLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class SongInfoService extends BaseService {
    @Autowired
    private SingerInfoService singerInfoService;
    @Autowired
    private CollectInfoService collectInfoService;
    @Autowired
    private CollectInfoRepository collectInfoRepository;
    @Autowired
    private SongInfoRepository songInfoRepository;
    @Autowired
    private SongStatisticsDayLogRepository songStatisticsDayLogRepository;
    @Autowired
    private  SongStatisticsDayLogService songStatisticsDayLogService;

    public SongInfo insertSong(SongInfo songInfo) {
        return songInfoRepository.saveAndFlush(songInfo);
    }


    /**
     * 不能模糊,可以sort排序
     *
     * @return
     */
    public Page<SongInfo> test() {
        SongInfo songInfo = new SongInfo();
        songInfo.setSingerId(123L);
        songInfoRepository.findAll(Example.of(songInfo), PageRequest.of(1, 2));
        songInfoRepository.findOne(Example.of(songInfo));
        return null;

    }


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

        return new PageImpl<>(songList, pageable, songStatisticsDayLogPage.getTotalPages());

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

        if (songStatisticsDayLogPage == null) {
            return null;
        }
        //find songs
        for (SongStatisticsDayLog songStatisticsDayLog : songStatisticsDayLogPage.getContent()) {
            SongInfo songInfo = songInfoRepository.findBySongId(songStatisticsDayLog.getSongId());
            songList.add(songInfo);
        }

        return new PageImpl<>(songList, pageable, songStatisticsDayLogPage.getTotalElements());
    }


    /**
     * 获取最新30天歌曲信息
     *
     * @param pageable
     * @return
     */
    public Page<SongInfo> getLast30DaysSongList(Pageable pageable) {
        Date date = new Date(System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000);
        return songInfoRepository.findAllByCreateTimeAfterOrderByCreateTimeDesc(date, pageable);
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
        Page<CollectInfo> collectInfoPage = collectInfoService.findCollectSong(pageable, userId);

        if (collectInfoPage == null) {
            return null;
        }

        for (CollectInfo collectInfo : collectInfoPage.getContent()) {
            SongInfo songInfo = songInfoRepository.findBySongId(collectInfo.getCollectId());
            songInfoList.add(songInfo);
        }

        Page<SongInfo> page = new PageImpl<>(songInfoList, pageable, collectInfoPage.getTotalElements());
        return page;

    }

    /**
     * 用户收藏的所有歌曲
     * @param userId
     * @return
     */
    public List<SongInfo> getCollectSongInfoList(@NonNull Long userId) {

        List<Long> collectSongIdList = collectInfoRepository.findAllUserCollectSongIdByUserId(userId);
        if (collectSongIdList == null) {
            return null;
        }

        List<SongInfo> collectSongInfo = songInfoRepository.findAllBySongIdIn(collectSongIdList);
        if (collectSongIdList == null) {
            return null;
        }

        return collectSongInfo;
    }

    /**
     * 根据用户收藏歌曲类型推荐
     *
     * @param userId
     * @param pageable
     * @return
     */
    public Page<SongInfo> recommendSongByTypePage(@NonNull Long userId, Pageable pageable) {
        List<Integer> mostLoveSongType = collectInfoRepository.findMostLoveSongType(userId);
        if (mostLoveSongType == null) {
            throw new ServiceException(ResponseCode.SUCC_1);
        }

        Page<SongInfo> recommendSongPage = songInfoRepository.findAllBySongType(mostLoveSongType, pageable);
        return recommendSongPage;
    }

    /**
     * 根据收藏的歌手前三名推荐其歌曲
     *
     * @param userId
     * @param pageable
     * @return
     */
    public Page<SongInfo> recommendSongBySinger(@NonNull Long userId, Pageable pageable) {
        List<SingerInfo> mostLoveSingerList = singerInfoService.findMost3LoveSinger(userId);
        if (mostLoveSingerList == null) {
            throw new ServiceException(ResponseCode.SUCC_1);
        }
        //歌手id
        List<Long> singerIdList = Lists.newArrayList();
        for (SingerInfo singerInfo : mostLoveSingerList) {
            singerIdList.add(singerInfo.getSingerId());
        }

        Page<SongInfo> page = songInfoRepository.findAllBySingerIdIn(singerIdList, pageable);
        return page;

    }


    public List<SongInfo> searchSongList(@NonNull String searchValue) {
        return songInfoRepository.findAllBySongNameLike("%" + searchValue + "%");
    }

    public Page<SongInfo> searchSongPage(@NonNull String searchValue,Pageable pageable) {
        return songInfoRepository.findAllBySongNameLike("%" + searchValue + "%",pageable);
    }


    /**
     * 歌曲点赞功能
     *
     * @param songId
     * @return
     */
    public SongInfo starSong(@NonNull Long songId) {
        SongInfo songInfo = songInfoRepository.findBySongId(songId);
        if (songInfo == null) {
            log.info("未找到此歌曲");
            return null;
        }
        //记录歌曲的总点赞数
        long songStar = songInfo.getSongStar() == null ? 0 : songInfo.getSongStar();
        songInfo.setSongStar(songStar + 1);
        songInfoRepository.save(songInfo);

        //更新点赞数
        songStatisticsDayLogService.updateStarNum(songId);

        return songInfo;
    }


    /**
     *
     * @param songId
     * @return
     */
    public SongInfo searchSongBySongId(@NonNull Long songId){
       return songInfoRepository.findBySongId(songId);
    }
}
