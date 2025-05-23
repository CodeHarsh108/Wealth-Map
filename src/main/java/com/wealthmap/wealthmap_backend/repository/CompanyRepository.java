package com.wealthmap.wealthmap_backend.repository;

import com.wealthmap.wealthmap_backend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
