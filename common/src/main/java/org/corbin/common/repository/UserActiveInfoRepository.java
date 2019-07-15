package org.corbin.common.repository;

import org.corbin.common.base.dao.BaseRepository;
import org.corbin.common.entity.UserActiveInfo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserActiveInfoRepository extends BaseRepository<UserActiveInfo, Long> {
    UserActiveInfo findByUserId(Long userId);

    @Transactional
    void deleteByUserId(Long userId);
}
