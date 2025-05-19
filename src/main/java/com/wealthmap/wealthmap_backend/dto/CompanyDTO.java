package com.wealthmap.wealthmap_backend.dto;

import lombok.Data;

@Data
public class CompanyDTO {
    private Long id;
    private String name;
    private String type;
    private String logoUrl;
    private String description;
    private String website;
    private Integer size;
}
