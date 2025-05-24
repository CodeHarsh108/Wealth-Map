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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    EmployeeAccount account = new EmployeeAccount();

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


    @Override
    @Transactional
    public void createAccount(AccountSetupDto dto) throws BadRequestException {
        // 1. Lookup employee by email
        Employee employee = employeeRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + dto.getEmail()));

        // 2. Check if account already exists (i.e. if password already set)
        if (employee.getPassword() != null) {
            throw new BadRequestException("Account is already set up for this employee.");
        }

        // 3. Create a new EmployeeAccount entity (optional separation for login/security logic)
        EmployeeAccount account = new EmployeeAccount();
        account.setEmployee(employee); // Link to the employee
        account.setPassword(passwordEncoder.encode(dto.getPassword())); // Secure password hash
        account.setCreatedAt(LocalDateTime.now());

        // 4. Save the account entity
        employeeAccountRepository.save(account);

        // 5. Optionally update employee status
        employee.setAccountCreated(true);
        employeeRepository.save(employee);

        // 6. Optionally create default notification settings
        EmployeeNotificationSetting settings = new EmployeeNotificationSetting();
        settings.setEmployee(employee);
        settings.setEmailNotificationsEnabled(true);
        settings.setPushNotificationsEnabled(false);
        notificationSettingRepository.save(settings);
    }

    @Override
    @Transactional
    public void setupMfa(MfaSetupDto dto) {
        EmployeeAccount account = employeeAccountRepository.findByEmployeeId(dto.getEmployeeId())
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeAccount not found for employee ID: " + dto.getEmployeeId()));

        account.setMfaEnabled(true);
        account.setMfaMethod(dto.getMethod());
        account.setMfaSecret(dto.getSecret());

        employeeAccountRepository.save(account);
    }

    @Override
    @Transactional
    public void acceptTermsOfService(TermsAcceptanceDto dto) {
        // Find the Employee first
        Employee employee = employeeRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + dto.getEmail()));

        // Then find their account
        EmployeeAccount account = employeeAccountRepository.findByEmployeeId(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for employee: " + dto.getEmail()));

        // Update terms acceptance
        account.setAcceptedTerms(dto.isAccepted());
        employeeAccountRepository.save(account);
    }
    @Override
    @Transactional
    public void completeTutorial(Long employeeId) {
        // Step 1 & 2: Find employee account or throw exception
        EmployeeAccount account = employeeAccountRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeAccount not found for employee ID: " + employeeId));

        // Step 3: Mark tutorial as completed
        account.setCompletedTutorial(true);

        // Step 4: Save updated account
        employeeAccountRepository.save(account);
    }
    @Override
    @Transactional
    public void updateNotificationPreferences(NotificationPreferenceDto dto) {
        // First find the employee by email
        Employee employee = employeeRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + dto.getEmail()));

        // Then find their account
        EmployeeAccount account = employeeAccountRepository.findByEmployeeId(employee.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for employee: " + dto.getEmail()));

        // Update the notification preference
        account.setNotificationPreference(dto.getPreference());

        // Save updated account
        employeeAccountRepository.save(account);
    }}
