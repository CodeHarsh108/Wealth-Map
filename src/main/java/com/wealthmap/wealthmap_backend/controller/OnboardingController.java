package com.wealthmap.wealthmap_backend.controller;

import com.wealthmap.wealthmap_backend.dto.*;
import com.wealthmap.wealthmap_backend.service.OnboardingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/onboarding")
@RequiredArgsConstructor
public class OnboardingController {

    private final OnboardingService onboardingService;

    @PostMapping("/accept-invite")
    public ResponseEntity<Void> acceptInvitation(@Valid @RequestBody AcceptInvitationDto dto) throws BadRequestException {
        onboardingService.acceptInvitation(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/create-account")
    public ResponseEntity<Void> createAccount(@Valid @RequestBody AccountSetupDto dto) throws BadRequestException{
        onboardingService.createAccount(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mfa/setup")
    public ResponseEntity<Void> setupMfa(@Valid @RequestBody MfaSetupDto dto) {
        onboardingService.setupMfa(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tos/accept")
    public ResponseEntity<Void> acceptTermsOfService(@Valid @RequestBody TermsAcceptanceDto dto) {
        onboardingService.acceptTermsOfService(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tutorial/complete")
    public ResponseEntity<Void> completeTutorial(@RequestParam Long employeeId) {
        onboardingService.completeTutorial(employeeId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/notifications")
    public ResponseEntity<Void> updateNotificationPrefs(@Valid @RequestBody NotificationPreferenceDto dto) {
        onboardingService.updateNotificationPreferences(dto);
        return ResponseEntity.ok().build();
    }
}
