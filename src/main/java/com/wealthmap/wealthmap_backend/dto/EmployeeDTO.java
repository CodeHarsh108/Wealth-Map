package com.wealthmap.wealthmap_backend.dto;

import lombok.Data;

@Data
public class EmployeeDTO {
    private Long id;
    private String name;
    private String email;
    private String role; // e.g., ADMIN, MANAGER, EMPLOYEE
    private boolean acceptedTerms;
    private Long companyId;
}
