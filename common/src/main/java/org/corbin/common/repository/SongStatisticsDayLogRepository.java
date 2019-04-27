package org.corbin.common.repository;

import org.corbin.common.entity.SongStatisticsDayLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
