package com.wealthmap.wealthmap_backend.model;

import com.wealthmap.wealthmap_backend.model.MfaMethod;
import com.wealthmap.wealthmap_backend.security.EncryptedStringConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employee_accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
//    @Email
//    @NotBlank(message = "Email is required")
//    private String email;


    @NotBlank(message = "Password is required")
    private String password;

    private boolean mfaEnabled;

    @Enumerated(EnumType.STRING)
    private MfaMethod mfaMethod;

    @Convert(converter = EncryptedStringConverter.class)
    private String mfaSecret;

    private boolean acceptedTerms;

    private boolean completedTutorial;

    private boolean emailVerified;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Enumerated(EnumType.STRING)
    private NotificationPreference notificationPreference;


}
