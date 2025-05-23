package com.wealthmap.wealthmap_backend.dto;

import com.wealthmap.wealthmap_backend.model.NotificationPreference;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationPreferenceDto {

    @Email(message = "Valid email is required")
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Notification preference is required")
    private NotificationPreference preference;
}
