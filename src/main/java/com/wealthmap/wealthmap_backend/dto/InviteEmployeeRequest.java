package com.wealthmap.wealthmap_backend.dto;

import lombok.Data;

@Data
public class InviteEmployeeRequest {
    private String email;
    private Long companyId;
}
