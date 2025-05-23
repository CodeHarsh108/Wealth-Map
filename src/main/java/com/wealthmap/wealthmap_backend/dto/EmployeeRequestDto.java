package com.wealthmap.wealthmap_backend.dto;

import com.wealthmap.wealthmap_backend.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequestDto {

    @NotBlank(message = "Employee name is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @Email(message = "Valid email is required")
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "Role must be specified")
    private Role role;
}
