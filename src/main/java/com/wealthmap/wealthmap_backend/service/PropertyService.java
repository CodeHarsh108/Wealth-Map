package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.dto.ClusterDTO;
import com.wealthmap.wealthmap_backend.dto.PolygonSearchDTO;
import com.wealthmap.wealthmap_backend.dto.PropertyDTO;
import com.wealthmap.wealthmap_backend.dto.PropertyResponse;

import java.util.List;


public interface PropertyService {
    PropertyResponse getAllProperties(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    PropertyDTO getPropertyById(Long id);

    PropertyDTO createProperty(PropertyDTO dto);

    PropertyDTO updateProperty(Long id, PropertyDTO dto);

    void deleteProperty(Long id);

    PropertyResponse filterByMinValue(double minValue, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    PropertyResponse filterByPropertyType(String type, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    PropertyResponse filterBySizeRange(double min, double max, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    PropertyResponse searchByOwnerName(String name, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    PropertyResponse filterByMapBounds(double minLat, double maxLat, double minLng, double maxLng,
                                       Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    PropertyResponse filterByDistance(double lat, double lng, double radiusInKm,
                                      int pageNumber, int pageSize,
                                      String sortBy, String sortOrder);


    List<ClusterDTO> getClusters(double lat, double lng, double radius, double gridSize);


    List<PropertyDTO> findPropertiesWithinPolygon(PolygonSearchDTO polygonSearchDTO);

    List<PropertyDTO> getPropertiesSortedByDistance(double lat, double lng, int limit);



}
