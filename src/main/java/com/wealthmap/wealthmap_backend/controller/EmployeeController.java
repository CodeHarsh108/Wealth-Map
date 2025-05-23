//package com.wealthmap.wealthmap_backend.controller;
//
//import com.wealthmap.wealthmap_backend.dto.EmployeeRequestDto;
//import com.wealthmap.wealthmap_backend.dto.EmployeeResponseDto;
//import com.wealthmap.wealthmap_backend.service.EmployeeService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/employees")
//@RequiredArgsConstructor
//public class EmployeeController {
//
//    private final EmployeeService employeeService;
//
//    // Invite (create) employee linked to a company
//    @PostMapping("/invite/{companyId}")
//    public ResponseEntity<EmployeeResponseDto> inviteEmployee(@PathVariable Long companyId, @Valid @RequestBody EmployeeRequestDto dto) {
//        EmployeeResponseDto response = employeeService.inviteEmployee(companyId, dto);
//        return ResponseEntity.ok(response);
//    }
//
//    // Get all employees for a company (for admin view)
//    @GetMapping("/company/{companyId}")
//    public ResponseEntity<List<EmployeeResponseDto>> getEmployeesByCompany(@PathVariable Long companyId) {
//        return ResponseEntity.ok(employeeService.getEmployeesByCompany(companyId));
//    }
//
//    // Update employee role / permissions
//    @PutMapping("/{employeeId}/role")
//    public ResponseEntity<EmployeeResponseDto> updateEmployeeRole(@PathVariable Long employeeId, @RequestParam String role) {
//        return ResponseEntity.ok(employeeService.updateEmployeeRole(employeeId, role));
//    }
//
//    // Revoke employee access (soft delete or deactivate)
//    @PutMapping("/{employeeId}/revoke")
//    public ResponseEntity<Void> revokeEmployeeAccess(@PathVariable Long employeeId) {
//        employeeService.revokeEmployeeAccess(employeeId);
//        return ResponseEntity.ok().build();
//    }
//
//    // Get employee activity details (last active etc)
//    @GetMapping("/{employeeId}/activity")
//    public ResponseEntity<EmployeeResponseDto> getEmployeeActivity(@PathVariable Long employeeId) {
//        return ResponseEntity.ok(employeeService.getEmployeeById(employeeId));
//    }
//}
