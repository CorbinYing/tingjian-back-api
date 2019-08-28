package org.corbin.common.repository;

import org.corbin.common.base.dao.BaseRepository;
import org.corbin.common.entity.SingerInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerInfoRepository extends BaseRepository<SingerInfo,Long> {

    /**
     * find Singer by singerName
     * @param singerName
     * @return
     */
    SingerInfo findBySingerName(String singerName);

    /**
     *
     * @param singerId
     * @return
     */
    SingerInfo findBySingerId(Long singerId);

    /**
     * 查找用户收藏的歌曲的歌手，没有去重，
     * 用户定位最喜欢的歌手
     * @return
     */
    @Query(value = "SELECT * FROM singer_info WHERE singer_id IN (SELECT song_info.singer_id FROM song_info,collect_info WHERE song_info.song_id=collect_info.collect_id and collect_info.user_id=?1)",nativeQuery = true)
    List<SingerInfo> findAllCollectSongInWithRepeat(Long userId);
}
