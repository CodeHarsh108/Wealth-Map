package com.wealthmap.wealthmap_backend.dto;

import com.wealthmap.wealthmap_backend.model.MfaMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MfaSetupDto {

    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @NotNull(message = "MFA method must be provided")
    private MfaMethod method;

    @NotBlank(message = "MFA secret must not be blank")
    private String secret;
}
