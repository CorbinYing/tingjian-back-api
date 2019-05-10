package org.corbin.server.repository;

import org.corbin.common.entity.SongStatisticsDayLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongStatisticsDayLogRepository extends JpaRepository<SongStatisticsDayLog, Long> {

}
