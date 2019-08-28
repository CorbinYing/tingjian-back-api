package org.corbin.common.repository;

import org.corbin.common.base.dao.BaseRepository;
import org.corbin.common.entity.VerificationLog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationLogRepository extends BaseRepository<VerificationLog, Long> {

    public List<VerificationLog> findByMailOrderByIdDesc(String mail);
}
