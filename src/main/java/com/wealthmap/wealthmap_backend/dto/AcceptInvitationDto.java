package com.wealthmap.wealthmap_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AcceptInvitationDto {

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @Email(message = "Valid email is required")
    @NotBlank(message = "Email is required")
    private String email;
}
