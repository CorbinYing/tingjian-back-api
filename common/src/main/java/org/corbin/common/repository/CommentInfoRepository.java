package org.corbin.common.repository;

import org.corbin.common.base.dao.BaseRepository;
import org.corbin.common.entity.CommentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentInfoRepository extends BaseRepository<CommentInfo,Long> {
    /**
     * find comment by songid list
     * @param songId
     * @return
     */
    List<CommentInfo> findAllBySongId(Long songId);
}
