package org.corbin.client.service;

import lombok.extern.slf4j.Slf4j;
import org.corbin.common.base.constant.EntityPreset;
import org.corbin.common.base.service.BaseService;
import org.corbin.common.entity.CollectInfo;
import org.corbin.common.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CollectInfoService extends BaseService {
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


    /**
     * 查找收藏的歌曲
     *
     * @param userId
     * @return
     */
    public Page<CollectInfo> findCollectSong(Long userId) {
        Integer collectType = EntityPreset.CollectType.song.getCollectTypeEncoding();
        return collectInfoRepository.findAllByUserIdAndCollectType(userId, collectType);
    }

    /**
     * 查找收藏的歌手
     *
     * @param userId
     * @return
     */
    public Page<CollectInfo> findCollectSinger(Long userId) {
        Integer collectType = EntityPreset.CollectType.singer.getCollectTypeEncoding();
        return collectInfoRepository.findAllByUserIdAndCollectType(userId, collectType);
    }


    /**
     * 查找收藏的歌单
     *
     * @param userId
     * @return
     */
    public Page<CollectInfo> findCollectSongOrder(Long userId) {
        Integer collectType = EntityPreset.CollectType.song_order.getCollectTypeEncoding();
        return collectInfoRepository.findAllByUserIdAndCollectType(userId, collectType);
    }


}
