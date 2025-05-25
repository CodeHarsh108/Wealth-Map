package com.wealthmap.wealthmap_backend.service;

public interface AccessLoggingService {
    void logAccess(Long employeeId, String action, String ipAddress, String userAgent);
}
