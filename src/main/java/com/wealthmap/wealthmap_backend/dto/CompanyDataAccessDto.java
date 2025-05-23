package com.wealthmap.wealthmap_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDataAccessDto {

    @NotNull(message = "Company ID is required")
    private Long companyId;

    private Set<String> preferences;
}
