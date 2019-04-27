package org.corbin.common.repository;

import org.corbin.common.entity.UserActiveInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActiveInfoRepository extends JpaRepository<UserActiveInfo, Long> {
    UserActiveInfo findByUserId(Long userId);
}
