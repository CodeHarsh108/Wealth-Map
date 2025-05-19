package com.wealthmap.wealthmap_backend.controller;

import com.wealthmap.wealthmap_backend.dto.AcceptInvitationRequest;
import com.wealthmap.wealthmap_backend.dto.EmployeeDTO;
import com.wealthmap.wealthmap_backend.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/accept-invitation")
    public ResponseEntity<EmployeeDTO> acceptInvitation(@RequestBody AcceptInvitationRequest request) {
        EmployeeDTO onboarded = employeeService.acceptInvitation(request);
        return ResponseEntity.ok(onboarded);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO dto = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/{id}/terms")
    public ResponseEntity<String> acceptTerms(@PathVariable Long id) {
        employeeService.acceptTerms(id);
        return ResponseEntity.ok("Terms of Service accepted");
    }
}
