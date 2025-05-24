package com.wealthmap.wealthmap_backend.repository;

import com.wealthmap.wealthmap_backend.model.EmployeeNotificationSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeNotificationSettingRepository extends JpaRepository<EmployeeNotificationSetting, Long> {
    Optional<EmployeeNotificationSetting> findByEmployeeId(Long employeeId);
}
