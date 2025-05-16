package com.wealthmap.wealthmap_backend.repository;

import com.wealthmap.wealthmap_backend.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

}
