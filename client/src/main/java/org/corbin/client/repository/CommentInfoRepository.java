package org.corbin.client.repository;

import org.corbin.common.entity.CommentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentInfoRepository extends JpaRepository<CommentInfo,Long> {
    /**
     * find comment by songid list
     * @param songId
     * @return
     */
    List<CommentInfo> findAllBySongId(Long songId);
}
