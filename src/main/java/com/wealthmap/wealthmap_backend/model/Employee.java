package com.wealthmap.wealthmap_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name should be between 2–100 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role must be defined")
    private Role role;

    @Column(nullable = false)
    private boolean invited;

    @Column(nullable = false)
    private boolean acceptedTerms;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
