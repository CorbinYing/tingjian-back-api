package org.corbin.client.repository;

import org.corbin.common.entity.SongInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongInfoRepository extends JpaRepository<SongInfo,Long> {

    SongInfo findBySongName(String songName);


}
