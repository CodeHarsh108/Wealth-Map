//package com.wealthmap.wealthmap_backend.repository;
//
//import com.wealthmap.wealthmap_backend.model.Employee;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    List<Employee> findByCompanyId(Long companyId);
//    boolean existsByEmail(String email); // Optional: avoid duplicate invites
//}
