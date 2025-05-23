package com.wealthmap.wealthmap_backend.dto;

import com.wealthmap.wealthmap_backend.model.CompanySize;
import com.wealthmap.wealthmap_backend.model.CompanyType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyRequestDto {

    @NotBlank(message = "Company name is required")
    @Size(max = 100, message = "Company name must be less than 100 characters")
    private String name;

    private String logoUrl;

    @Size(max = 1000, message = "Description must be less than 1000 characters")
    private String description;

    private String website;

    @NotNull(message = "Company size is required")
    private CompanySize size;

    @NotNull(message = "Company type is required")
    private CompanyType type;
}
