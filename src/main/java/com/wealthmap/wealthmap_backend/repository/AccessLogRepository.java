package com.wealthmap.wealthmap_backend.repository;

import com.wealthmap.wealthmap_backend.model.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
}
