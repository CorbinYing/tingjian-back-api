package org.corbin.server.repository;

import org.corbin.common.entity.SongInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface SongInfoRepository extends JpaRepository<SongInfo,Long> {

    List<SongInfo> findAllBySongShelfTimeAfterOrderByCreateTime(Date date);

    @Query("SELECT a FROM SongInfo a WHERE  a.songId not in (SELECT b.songId FROM SongStatisticsDayLog b ) ")
    List<SongInfo> findAllNeedAddIntoLog();




}
