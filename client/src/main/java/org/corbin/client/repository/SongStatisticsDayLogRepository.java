package org.corbin.client.repository;

import org.corbin.common.entity.SongStatisticsDayLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongStatisticsDayLogRepository extends JpaRepository<SongStatisticsDayLog, Long> {

    /**
     * 热度降序
     *
     * @param pageable
     * @return
     */
    Page<SongStatisticsDayLog> findAllByOrderByHotPointDesc(Pageable pageable);

    /**
     * 推荐度降序
     *
     * @param pageable
     * @return
     */
    Page<SongStatisticsDayLog> findAllByOrderByRecommendPointDesc(Pageable pageable);

    /**
     * 根据歌曲id查询统计数据
     * @param songId
     * @return
     */
    SongStatisticsDayLog findBySongId(Long songId);
}
