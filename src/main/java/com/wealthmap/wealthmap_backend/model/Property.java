package com.wealthmap.wealthmap_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;


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
    @NotNull(message = "Address must be selected")
    @Size(max = 100, message = "Address must be less than 100 characters")
    private String address;

    @NotNull(message = "City must be selected")
    @Size(max = 50, message = "City must be less than 50 characters")
    private String city;

    @NotNull(message = "State must be selected")
    @Size(max = 50, message = "State must be less than 50 characters")
    private String state;

    @NotNull(message = "Zipcode must be selected")
    @Pattern(regexp = "^\\d{5}(-\\d{4})?$", message = "Invalid Zip Code format")
    private String zipCode;

    // 🔹 Geo Info
    @NotNull
    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;

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
