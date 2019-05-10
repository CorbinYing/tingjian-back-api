package org.corbin.client.service;

import lombok.extern.slf4j.Slf4j;
import org.corbin.client.repository.CollectInfoRepository;
import org.corbin.client.repository.SingerInfoRepository;
import org.corbin.client.repository.SongInfoRepository;
import org.corbin.common.base.constant.EntityPreset;
import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.CollectInfo;
import org.corbin.common.entity.SongInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CollectInfoService extends BaseService {
    @Autowired
    private CollectInfoRepository collectInfoRepository;
    @Autowired
    private SongInfoRepository songInfoRepository;
    @Autowired
    private SingerInfoRepository singerInfoRepository;


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

//    /**
//     * 编辑收藏信息，添加或删除
//     * @param userId
//     * @param songId
//     * @return
//     */
//    public CollectInfo editCollectSong(@NonNull Long userId, @NonNull Long songId) {
//        CollectInfo collectInfo = new CollectInfo();
//        collectInfo.setUserId(userId);
//        collectInfo.setCollectId(songId);
//
//        Optional<CollectInfo> collect = collectInfoRepository.findOne(Example.of(collectInfo));
//        if (collect.isPresent()) {
//            delCollectSong(collect.get());
//        } else {
//            insertCollectSong(collectInfo);
//        }
//        return collect.isPresent() ? collect.get() : collectInfo;
//
//    }

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

        return collectInfoRepository.saveAndFlush(collectInfo);
    }


}
