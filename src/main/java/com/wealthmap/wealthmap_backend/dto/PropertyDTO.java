package com.wealthmap.wealthmap_backend.dto;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDTO {

    private Long id;

    // Location Info
    private double latitude;
    private double longitude;

    //  Property Details
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String propertyType;
    private double sizeInSqFt;
    private double value;

    // Owner Info
    private String ownerName;

    //  Company Info
    private String companyName;

    // Wealth Info
    private Double estimatedNetWorth;
}
