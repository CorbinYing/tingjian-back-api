package org.corbin.client.repository;

import org.corbin.common.entity.SingerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SingerInfoRepository extends JpaRepository<SingerInfo,Long> {
}
