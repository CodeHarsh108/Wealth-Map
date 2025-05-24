package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.dto.*;
import com.wealthmap.wealthmap_backend.model.*;
import com.wealthmap.wealthmap_backend.repository.*;
import com.wealthmap.wealthmap_backend.service.OnboardingService;
import com.wealthmap.wealthmap_backend.config.MapperConfig;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class OnboardingServiceImpl implements OnboardingService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeAccountRepository employeeAccountRepository;
    private final EmployeeNotificationSettingRepository notificationSettingRepository;
    private final MapperConfig mapperConfig;

    @Override
    @Transactional
    public void acceptInvitation(AcceptInvitationDto dto) throws BadRequestException {
        // 1. Look up employee by ID
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee with ID " + dto.getEmployeeId() + " not found."));

        // 2. Check if email matches invitation
        if (!employee.getEmail().equalsIgnoreCase(dto.getEmail())) {
            throw new BadRequestException("The provided email does not match our records.");
        }

        // 3. Prevent double acceptance
        if (Boolean.TRUE.equals(employee.getAcceptedInvite())) {
            throw new BadRequestException("This invitation has already been accepted.");
        }

        // 4. Update invitation status
        employee.setAcceptedInvite(true);
        employee.setInvitationAcceptedAt(LocalDateTime.now());
        log.info("Employee {} accepted the invitation at {}", employee.getId(), employee.getInvitationAcceptedAt());

        // 5. Persist changes
        employeeRepository.save(employee);
    }

//
//    @Override
//    @Transactional
//    public void createAccount(AccountSetupDto dto) {
//        // Implementation logic to create account
//    }
//
//    @Override
//    @Transactional
//    public void setupMfa(MfaSetupDto dto) {
//        // Implementation logic to setup MFA
//    }
//
//    @Override
//    @Transactional
//    public void acceptTermsOfService(TosAcceptanceDto dto) {
//        // Implementation logic to accept terms of service
//    }
//
//    @Override
//    @Transactional
//    public void completeTutorial(Long employeeId) {
//        // Implementation logic to complete tutorial
//    }
//
//    @Override
//    @Transactional
//    public void updateNotificationPreferences(NotificationPreferencesDto dto) {
//        // Implementation logic to update notification preferences
//    }
}
