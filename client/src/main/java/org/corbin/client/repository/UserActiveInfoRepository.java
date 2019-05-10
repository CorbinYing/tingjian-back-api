package org.corbin.client.repository;

import org.corbin.common.entity.UserActiveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserActiveInfoRepository extends JpaRepository<UserActiveInfo, Long> {
    UserActiveInfo findByUserId(Long userId);

    @Transactional
    void deleteByUserId(Long userId);
}
