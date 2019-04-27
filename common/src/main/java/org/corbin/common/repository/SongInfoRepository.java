package org.corbin.common.repository;

import org.corbin.common.entity.SongInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Date;

public interface SongInfoRepository extends JpaRepository<SongInfo, Long>, JpaSpecificationExecutor<SongInfo> {

    SongInfo findBySongName(String songName);

    SongInfo findBySongId(Long songId);

    Page<SongInfo> findAllBySongShelfTimeAfterAndOrderByCreateTime(Date date, Pageable pageable);

}
