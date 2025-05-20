package com.wealthmap.wealthmap_backend.dto;

import com.wealthmap.wealthmap_backend.model.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDTO{
    private Long id;
    private String name;
    private String email;
    private Role role;
    private boolean invited;
    private boolean acceptedTerms;
}
