package com.wealthmap.wealthmap_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password; // hashed

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean invited;

    private boolean acceptedTerms;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
