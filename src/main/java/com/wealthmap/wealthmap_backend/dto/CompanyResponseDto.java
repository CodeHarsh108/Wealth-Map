package com.wealthmap.wealthmap_backend.dto;

import com.wealthmap.wealthmap_backend.model.CompanySize;
import com.wealthmap.wealthmap_backend.model.CompanyType;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponseDto {

    private Long id;
    private String name;
    private String logoUrl;
    private String description;
    private String website;
    private CompanySize size;
    private CompanyType type;
    private Set<String> dataAccessPreferences;
}
