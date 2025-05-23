package com.wealthmap.wealthmap_backend.dto;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDTO {

    private Long id;

    // 🔹 Basic Info
    @NotNull(message = "Address must be selected")
    @Size(max = 100, message = "Address must be less than 100 characters")
    private String address;

    @NotNull(message = "City must be selected")
    @Size(max = 50, message = "City must be less than 50 characters")
    private String city;

    @NotNull(message = "State must be selected")
    @Size(max = 50, message = "State must be less than 50 characters")
    private String state;

    @Pattern(regexp = "^\\d{5}(-\\d{4})?$", message = "Invalid Zip Code format")
    private String zipCode;

    // 🔹 Geo Info
    @NotNull(message = "Latitude must be selected")
    @DecimalMin(value = "-90.0", message = "Latitude must be ≥ -90")
    @DecimalMax(value = "90.0", message = "Latitude must be ≤ 90")
    private Double latitude;

    @NotNull(message = "Longitude must be selected")
    @DecimalMin(value = "-180.0", message = "Longitude must be ≥ -180")
    @DecimalMax(value = "180.0", message = "Longitude must be ≤ 180")
    private Double longitude;

    // 🔹 Property Characteristics
    @NotNull(message = "Value must be selected")
    @Min(value = 0, message = "Value must be ≥ 0")
    private Double value;

    @NotNull(message = "Size must be selected")
    @Min(value = 0, message = "Size in SqFt must be ≥ 0")
    private Double sizeInSqFt;

    @Size(max = 50, message = "Property Type must be less than 50 characters")
    private String propertyType;

    // 🔹 Ownership Info
    @NotNull(message = "Owner must be selected")
    @Size(max = 100, message = "Owner Name must be less than 100 characters")
    private String ownerName;

    @Size(max = 254, message = "Owner Contact must be less than 254 characters")
    private String ownerContact;

    // 🔹 Company/Investor Info (optional)
    @Size(max = 100, message = "Company Name must be less than 100 characters")
    private String companyName;

    @Size(max = 100, message = "Company Type must be less than 100 characters")
    private String companyType;

    // 🔹 Net Worth Info
    @DecimalMin(value = "0.0", message = "Estimated Net Worth must be ≥ 0")
    private Double estimatedNetWorth;
}
