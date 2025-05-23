package com.wealthmap.wealthmap_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TermsAcceptanceDto {

    @Email(message = "Valid email is required")
    @NotNull(message = "Email must be provided")
    private String email;

    private boolean accepted;
}
