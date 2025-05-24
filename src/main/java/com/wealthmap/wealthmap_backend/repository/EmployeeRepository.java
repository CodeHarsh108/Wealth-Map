package com.wealthmap.wealthmap_backend.repository;

import com.wealthmap.wealthmap_backend.model.Employee;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByCompanyId(Long companyId);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employees WHERE company_id = :companyId", nativeQuery = true)
    void deleteEmployeesByCompanyId(@Param("companyId") Long companyId);


    Optional<Employee> findByEmail(String email);

}
