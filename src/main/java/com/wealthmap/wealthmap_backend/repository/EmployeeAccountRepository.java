package com.wealthmap.wealthmap_backend.repository;

import com.wealthmap.wealthmap_backend.model.EmployeeAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccount, Long> {
    Optional<EmployeeAccount> findByEmployeeId(Long employeeId);
}
