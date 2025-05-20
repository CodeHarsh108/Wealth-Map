package com.wealthmap.wealthmap_backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRegistrationRequestDTO{

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank
    private String name;

    @AssertTrue(message = "You must accept the terms to register")
    private boolean acceptedTerms;
}
