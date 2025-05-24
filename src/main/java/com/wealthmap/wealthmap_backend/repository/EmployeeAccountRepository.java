package com.wealthmap.wealthmap_backend.repository;

import com.wealthmap.wealthmap_backend.model.EmployeeAccount;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeAccountRepository extends JpaRepository<EmployeeAccount, Long> {
    Optional<EmployeeAccount> findByEmployeeId(Long employeeId);
    Optional<EmployeeAccount> findByEmployee_Email(String email);

}
