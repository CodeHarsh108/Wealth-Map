package com.wealthmap.wealthmap_backend.repository;

import com.wealthmap.wealthmap_backend.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByCompanyId(Long companyId);
    boolean existsByEmail(String email);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM employees WHERE company_id = :companyId", nativeQuery = true)
    void deleteEmployeesByCompanyId(@Param("companyId") Long companyId);


}


