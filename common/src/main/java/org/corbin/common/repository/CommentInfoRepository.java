package org.corbin.common.repository;

import org.corbin.common.entity.CommentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentInfoRepository extends JpaRepository<CommentInfo,Long> {
}
