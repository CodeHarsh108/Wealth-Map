package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.dto.*;
import org.apache.coyote.BadRequestException;

public interface OnboardingService {

    void acceptInvitation(AcceptInvitationDto dto) throws BadRequestException;

    void createAccount(AccountSetupDto dto) throws BadRequestException;

    void setupMfa(MfaSetupDto dto);

    void acceptTermsOfService(TermsAcceptanceDto dto);

    void completeTutorial(Long employeeId);

    void updateNotificationPreferences(NotificationPreferenceDto dto);
}
