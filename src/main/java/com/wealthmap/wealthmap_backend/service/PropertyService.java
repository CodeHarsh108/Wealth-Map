package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.dto.PropertyDTO;
import com.wealthmap.wealthmap_backend.dto.PropertyResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PropertyService {
    PropertyResponse getAllProperties(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    PropertyDTO getPropertyById(Long id);

    PropertyDTO createProperty(PropertyDTO dto);

    PropertyDTO updateProperty(Long id, PropertyDTO dto);

    void deleteProperty(Long id);

    List<PropertyDTO> filterByMinValue(double minValue);

    List<PropertyDTO> filterByPropertyType(String type);

    List<PropertyDTO> filterBySizeRange(double min, double max);

    List<PropertyDTO> searchByOwnerName(String name);

    List<PropertyDTO> filterByMapBounds(double minLat, double maxLat, double minLng, double maxLng);
}
