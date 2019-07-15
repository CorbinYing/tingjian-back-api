package org.corbin.common.service;

import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.constant.EntityPreset;
import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.CollectInfo;
import org.corbin.common.entity.SongInfo;
import org.corbin.common.repository.CollectInfoRepository;
import org.corbin.common.repository.SingerInfoRepository;
import org.corbin.common.repository.SongInfoRepository;
import org.corbin.common.repository.SongStatisticsDayLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CollectInfoService extends BaseService<CollectInfo,Long> {
    @Autowired
    private CollectInfoRepository collectInfoRepository;
    @Autowired
    private SongInfoRepository songInfoRepository;
    @Autowired
    private SingerInfoRepository singerInfoRepository;
    @Autowired
    private SongStatisticsDayLogRepository songStatisticsDayLogRepository;
    @Autowired
    private  SongStatisticsDayLogService songStatisticsDayLogService;


    /**
     * 查找收藏的歌曲信息
     *
     * @param userId
     * @return
     */
    public Page<CollectInfo> findCollectSong(Pageable pageable, Long userId) {
        Integer collectType = EntityPreset.CollectType.song.getCollectTypeEncoding();

        Page<CollectInfo> collectInfoPage = collectInfoRepository.findAllByUserIdAndCollectType(pageable, userId, collectType);
        if (!collectInfoPage.hasContent()) {
            return null;
        }
        return collectInfoPage;
    }

    /**
     * 查找收藏的歌手
     *
     * @param userId
     * @return
     */
    public Page<CollectInfo> findCollectSinger(Pageable pageable, Long userId) {
        Integer collectType = EntityPreset.CollectType.singer.getCollectTypeEncoding();
        return collectInfoRepository.findAllByUserIdAndCollectType(pageable, userId, collectType);
    }


    /**
     * 查找收藏的歌单
     *
     * @param userId
     * @return
     */
    public Page<CollectInfo> findCollectSongOrder(Pageable pageable, Long userId) {
        Integer collectType = EntityPreset.CollectType.song_order.getCollectTypeEncoding();
        return collectInfoRepository.findAllByUserIdAndCollectType(pageable, userId, collectType);
    }



    /**
     * 删除收藏歌曲
     *
     * @return
     */
    public void delCollectSong(Long userId, Long songId) {
        CollectInfo collectInfo = collectInfoRepository.findByUserIdAndCollectId(userId, songId);
        if (collectInfo == null) {
            log.info("用户未收藏此歌曲");
            return;
        }
        collectInfoRepository.delete(collectInfo);

    }

    /**
     * add collect song
     *
     * @param collectInfo
     * @return
     */
    public CollectInfo insertCollectSong(@NonNull CollectInfo collectInfo) {
        return collectInfoRepository.saveAndFlush(collectInfo);
    }

    /**
     * add collect song
     *
     * @param userId
     * @param songId
     * @return
     */
    public CollectInfo insertCollectSong(@NonNull Long userId, @NonNull Long songId) {
        SongInfo songInfo = songInfoRepository.findBySongId(songId);
        if (songInfo == null) {
            log.info("无此歌曲");
            return null;
        }
        CollectInfo collectInfo = collectInfoRepository.findByUserIdAndCollectId(userId, songId);
        if (collectInfo != null) {
            return collectInfo;
        }

        collectInfo = new CollectInfo();
        collectInfo.setCollectId(songId);
        collectInfo.setUserId(userId);
        collectInfo.setCollectType(EntityPreset.CollectType.song.getCollectTypeEncoding());
        collectInfo.setCollectSongType(songInfo.getSongType());

        //更新收藏次数
        songStatisticsDayLogService.updateCollectNum(songId);

        return collectInfoRepository.saveAndFlush(collectInfo);
    }


}
