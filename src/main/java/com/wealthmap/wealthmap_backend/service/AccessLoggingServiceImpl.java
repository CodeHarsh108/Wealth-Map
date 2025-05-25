package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.model.AccessLog;
import com.wealthmap.wealthmap_backend.repository.AccessLogRepository;
import com.wealthmap.wealthmap_backend.service.AccessLoggingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccessLoggingServiceImpl implements AccessLoggingService {

    private final AccessLogRepository accessLogRepository;

    @Override
    public void logAccess(Long employeeId, String action, String ipAddress, String userAgent) {
        AccessLog log = AccessLog.builder()
                .employeeId(employeeId)
                .action(action)
                .ipAddress(ipAddress)
                .userAgent(userAgent)
                .build();
        accessLogRepository.save(log);
    }
}
