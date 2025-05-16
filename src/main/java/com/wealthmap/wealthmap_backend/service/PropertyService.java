package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.dto.PropertyDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PropertyService {
    List<PropertyDTO> getAllProperties();

    PropertyDTO getPropertyById(Long id);
}
