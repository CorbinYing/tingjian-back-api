package org.corbin.client.repository;

import org.corbin.common.entity.CommentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentInfoRepository extends JpaRepository<CommentInfo,Long> {
}
