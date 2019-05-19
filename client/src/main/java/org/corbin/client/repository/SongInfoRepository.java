package org.corbin.client.repository;

import org.corbin.common.entity.SongInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SongInfoRepository extends JpaRepository<SongInfo, Long>, JpaSpecificationExecutor<SongInfo> {

    SongInfo findBySongName(String songName);

    SongInfo findBySongId(Long songId);
    List<SongInfo> findAllBySongIdIn(List<Long> songIdList);

    Page<SongInfo> findAllByCreateTimeAfterOrderByCreateTimeDesc(Date date, Pageable pageable);

    //  Page<SongInfo> findAllBySongIdBetween(Integer id1,Integer id2,Pageable pageable);



    List<SongInfo> findAllBySongNameLike(String songName);
    Page<SongInfo> findAllBySongNameLike(String songName,Pageable pageable);

    /**
     * 根据歌曲类型查找
     * @return
     */
    Page<SongInfo>findAllBySongType(List<Integer> songTypeList,Pageable pageable);

    /**
     *
     * @param singerIdList
     * @param pageable
     * @return
     */
    Page<SongInfo>findAllBySingerIdIn(List<Long> singerIdList,Pageable pageable);


}
