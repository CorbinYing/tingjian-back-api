package org.corbin.client.repository;

import org.corbin.common.entity.CommentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentInfoRepository extends JpaRepository<CommentInfo,Long> {
}
