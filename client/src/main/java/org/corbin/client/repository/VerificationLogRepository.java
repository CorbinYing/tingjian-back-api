package org.corbin.client.repository;

import org.corbin.common.entity.VerificationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationLogRepository extends JpaRepository<VerificationLog, Long> {

    public List<VerificationLog> findByMailOrderByIdDesc(String mail);
}
