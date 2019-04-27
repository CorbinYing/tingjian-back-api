package org.corbin.common.repository;

import org.corbin.common.entity.CollectInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectInfoRepository extends JpaRepository<CollectInfo,Long> {

    /**
     * 查找我的收藏
     * @param user
     * @param collectType
     * @return
     */
    Page<CollectInfo> findAllByUserIdAndCollectType(Long user,Integer collectType);
}
