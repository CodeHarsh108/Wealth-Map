package com.wealthmap.wealthmap_backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employee_notification_settings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeNotificationSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NotificationPreference preference;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Setter
    @Getter
    private boolean emailNotificationsEnabled;
    @Setter
    @Getter
    private boolean pushNotificationsEnabled;

}
