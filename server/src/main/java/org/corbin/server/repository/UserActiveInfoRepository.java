package org.corbin.server.repository;

import org.corbin.common.entity.UserActiveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActiveInfoRepository extends JpaRepository<UserActiveInfo, Long> {
    UserActiveInfo findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
