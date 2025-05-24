package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.dto.*;
import org.apache.coyote.BadRequestException;

public interface OnboardingService {

    void acceptInvitation(AcceptInvitationDto dto) throws BadRequestException;

//    void createAccount(AccountSetupDto dto);
//
//    void setupMfa(MfaSetupDto dto);
//
//    void acceptTermsOfService(TosAcceptanceDto dto);
//
//    void completeTutorial(Long employeeId);
//
//    void updateNotificationPreferences(NotificationPreferencesDto dto);
}
