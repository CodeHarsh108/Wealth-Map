package com.wealthmap.wealthmap_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeInviteRequestDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String name;
}
