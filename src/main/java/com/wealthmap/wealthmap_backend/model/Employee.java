package com.wealthmap.wealthmap_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Employee name is required")
    @Size(max = 255, message = "Name must be less than 255 characters")
    private String name;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role must be specified")
    private Role role;

    private boolean active;

    private LocalDateTime joinedAt;

    private LocalDateTime lastActiveAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @PrePersist
    public void prePersist() {
        this.joinedAt = LocalDateTime.now();
        this.active = true;
    }

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private EmployeeAccount account;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private EmployeeNotificationSetting notificationSetting;

    @Column(name = "accepted_invite", nullable = false)
    private Boolean acceptedInvite = false;

    @Column(name = "invitation_accepted_at")
    private LocalDateTime invitationAcceptedAt;

}
