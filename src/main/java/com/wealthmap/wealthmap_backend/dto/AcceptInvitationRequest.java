package com.wealthmap.wealthmap_backend.dto;

import lombok.Data;

@Data
public class AcceptInvitationRequest {
    private String email;
    private String password;
    private String name;
}
