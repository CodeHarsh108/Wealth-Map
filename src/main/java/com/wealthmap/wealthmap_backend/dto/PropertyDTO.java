package com.wealthmap.wealthmap_backend.dto;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDTO {

    private Long id;

    // Location Info
    @DecimalMin(value = "-90.0", message = "Latitude must be ≥ -90")
    @DecimalMax(value = "90.0", message = "Latitude must be ≤ 90")
    private double latitude;

    @DecimalMin(value = "-180.0", message = "Longitude must be ≥ -180")
    @DecimalMax(value = "180.0", message = "Longitude must be ≤ 180")
    private double longitude;

    @Size(max = 100, message = "Address must be less than 100 characters")
    private String address;

    @Size(max = 50, message = "City must be less than 50 characters")
    private String city;

    @Size(max = 50, message = "State must be less than 50 characters")
    private String state;

    @Size(max = 5, message = "ZipCode must be less than 5 characters")
    private String zipCode;

    @Size(max = 50, message = "Property type must be less than 50 characters")
    private String propertyType;

    @Min(value = 0, message = "Size in SqFt must be ≥ 0")
    private double sizeInSqFt;

    @Min(value = 0, message = "Property value must be ≥ 0")
    private double value;

    @Size(max = 100, message = "Owner name must be less than 100 characters")
    private String ownerName;

    @Size(max = 100, message = "Company name must be less than 100 characters")
    private String companyName;

    @DecimalMin(value = "0.0", message = "Estimated net worth must be ≥ 0")
    private Double estimatedNetWorth;
}
