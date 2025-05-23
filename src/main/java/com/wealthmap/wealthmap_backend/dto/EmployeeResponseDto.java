package com.wealthmap.wealthmap_backend.dto;

import com.wealthmap.wealthmap_backend.model.Role;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponseDto {

    private Long id;
    private String name;
    private String email;
    private Role role;
    private boolean active;
    private LocalDateTime joinedAt;
    private LocalDateTime lastActiveAt;
}
