package com.wealthmap.wealthmap_backend.dto;

import com.wealthmap.wealthmap_backend.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeePermissionUpdateDTO {

    @NotNull
    private Role newRole;
}
