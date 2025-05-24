package com.wealthmap.wealthmap_backend.repository;

import com.wealthmap.wealthmap_backend.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    @Modifying
    @Query("DELETE FROM Company c WHERE c.id = :id")
    void deleteCompanyById(@Param("id") Long id);
}
