package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.dto.GeocodingResponseDTO;

public interface GeocodingService {
    GeocodingResponseDTO geocodeAddress(String address);
    GeocodingResponseDTO reverseGeocode(double lat, double lng);
}
