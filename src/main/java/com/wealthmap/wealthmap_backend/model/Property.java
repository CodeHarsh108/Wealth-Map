package com.wealthmap.wealthmap_backend.model;

import jakarta.persistence.*;
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
    private String address;
    private String city;
    private String state;
    private String zipCode;

    // 🔹 Geo Info
    private double latitude;
    private double longitude;

    // 🔹 Property Characteristics
    private double value;         // From Zillow or ReportAll
    private double sizeInSqFt;
    private String propertyType; // e.g., Commercial, Residential, Industrial

    // 🔹 Ownership Info
    private String ownerName;     // May come from FastPeopleSearch, ReportAll
    private String ownerContact;  // Optional, from ZoomInfo / FPS

    // 🔹 Company/Investor Info (optional)
    private String companyName;   // From PitchBook / ZoomInfo
    private String companyType;

    // 🔹 Net Worth Info
    private Double estimatedNetWorth; // From Wealth Engine
}
