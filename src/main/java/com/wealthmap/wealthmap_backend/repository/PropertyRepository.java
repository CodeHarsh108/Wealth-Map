package com.wealthmap.wealthmap_backend.repository;

import com.wealthmap.wealthmap_backend.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Page<Property> findByValueGreaterThanEqual(double minValue, Pageable pageable);

    Page<Property> findByPropertyTypeIgnoreCase(String type, Pageable pageable);

    Page<Property> findBySizeInSqFtBetween(double min, double max, Pageable pageable);

    Page<Property> findByOwnerNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Property> findByLatitudeBetweenAndLongitudeBetween(double minLat, double maxLat, double minLng, double maxLng, Pageable pageable);
}
