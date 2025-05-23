package com.wealthmap.wealthmap_backend.dto;

import com.wealthmap.wealthmap_backend.model.NotificationPreference;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeOnboardingStatusDto {

    private boolean accountCreated;
    private boolean mfaEnabled;
    private boolean termsAccepted;
    private boolean tutorialCompleted;
    private boolean emailVerified;
    private NotificationPreference notificationPreference;
}
