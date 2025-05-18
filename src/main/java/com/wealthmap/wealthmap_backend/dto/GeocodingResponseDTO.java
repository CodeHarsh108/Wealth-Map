package com.wealthmap.wealthmap_backend.dto;

import lombok.Data;

@Data
public class GeocodingResponseDTO {
    private double latitude;
    private double longitude;
    private String formattedAddress;
}
