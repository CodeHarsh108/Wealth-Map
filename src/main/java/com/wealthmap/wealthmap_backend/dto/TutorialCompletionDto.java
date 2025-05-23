package com.wealthmap.wealthmap_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TutorialCompletionDto {

    @Email(message = "Valid email is required")
    @NotNull(message = "Email is required")
    private String email;

    private boolean completed;
}
