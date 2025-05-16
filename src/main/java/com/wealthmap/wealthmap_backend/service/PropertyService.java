package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.dto.PropertyDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PropertyService {
    List<PropertyDTO> getAllProperties();

    PropertyDTO getPropertyById(Long id);

    PropertyDTO createProperty(PropertyDTO dto);

    PropertyDTO updateProperty(Long id, PropertyDTO dto);

    void deleteProperty(Long id);

    List<PropertyDTO> filterByMinValue(double minValue);

    List<PropertyDTO> filterByPropertyType(String type);

    List<PropertyDTO> filterBySizeRange(double min, double max);

    List<PropertyDTO> searchByOwnerName(String name);
}
