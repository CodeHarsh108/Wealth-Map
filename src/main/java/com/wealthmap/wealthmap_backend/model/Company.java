package com.wealthmap.wealthmap_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name must be less than 100 characters")
    private String name;

    private String logoUrl;

    @Size(max = 1000, message = "Description can be up to 1000 characters")
    private String description;

    private String website;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Company size must be specified")
    private CompanySize size;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Company type must be specified")
    private CompanyType type;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "company_data_preferences", joinColumns = @JoinColumn(name = "company_id"))
    @Column(name = "preference")
    @Builder.Default
    private Set<String> dataAccessPreferences = new HashSet<>();
}
