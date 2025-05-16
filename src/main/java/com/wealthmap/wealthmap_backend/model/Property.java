package com.wealthmap.wealthmap_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "property")
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔹 Basic Info
    @Size(max = 100, message = "Address must be less than 100 characters")
    private String address;

    @Size(max = 50, message = "City must be less than 50 characters")
    private String city;

    @Size(max = 50, message = "State must be less than 50 characters")
    private String state;

    @Size(max = 5, message = "ZipCode must be less than 5 characters")
    private String zipCode;

    // 🔹 Geo Info
    @DecimalMin(value = "-90.0", message = "Latitude must be ≥ -90")
    @DecimalMax(value = "90.0", message = "Latitude must be ≤ 90")
    private double latitude;

    @DecimalMin(value = "-180.0", message = "Longitude must be ≥ -180")
    @DecimalMax(value = "180.0", message = "Longitude must be ≤ 180")
    private double longitude;

    // 🔹 Property Characteristics
    @Min(value = 0, message = "Value must be ≥ 0")
    private double value;

    @Min(value = 0, message = "Size in SqFt must be ≥ 0")
    private double sizeInSqFt;

    @Size(max = 50, message = "Property Type must be less than 50 characters")
    private String propertyType;

    // 🔹 Ownership Info
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
