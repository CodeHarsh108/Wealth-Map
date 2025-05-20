package com.wealthmap.wealthmap_backend.dto;

import com.wealthmap.wealthmap_backend.model.CompanyType;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyResponseDTO {

    private Long id;
    private String name;
    private CompanyType type;
    private String logoUrl;
    private String description;
    private String website;
    private Integer size;
}
