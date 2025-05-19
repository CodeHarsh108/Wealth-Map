package com.wealthmap.wealthmap_backend.controller;

import com.wealthmap.wealthmap_backend.dto.CompanyDTO;
import com.wealthmap.wealthmap_backend.dto.EmployeeDTO;
import com.wealthmap.wealthmap_backend.dto.InviteEmployeeRequest;
import com.wealthmap.wealthmap_backend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/register")
    public ResponseEntity<CompanyDTO> registerCompany(@RequestBody CompanyDTO companyDTO) {
        CompanyDTO createdCompany = companyService.registerCompany(companyDTO);
        return ResponseEntity.ok(createdCompany);
    }

    @PostMapping("/{companyId}/invite")
    public ResponseEntity<String> inviteEmployee(@PathVariable Long companyId,
                                                 @RequestBody InviteEmployeeRequest inviteRequest) {
        companyService.inviteEmployee(companyId, inviteRequest);
        return ResponseEntity.ok("Invitation sent to " + inviteRequest.getEmail());
    }

    @GetMapping("/{companyId}/employees")
    public ResponseEntity<List<EmployeeDTO>> getCompanyEmployees(@PathVariable Long companyId) {
        List<EmployeeDTO> employees = companyService.getEmployeesByCompany(companyId);
        return ResponseEntity.ok(employees);
    }

    @DeleteMapping("/{companyId}/employee/{employeeId}")
    public ResponseEntity<Void> removeEmployee(@PathVariable Long companyId,
                                               @PathVariable Long employeeId) {
        companyService.removeEmployee(companyId, employeeId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{companyId}/employee/{employeeId}/role")
    public ResponseEntity<String> updateEmployeeRole(@PathVariable Long companyId,
                                                     @PathVariable Long employeeId,
                                                     @RequestParam String role) {
        companyService.updateEmployeeRole(companyId, employeeId, role);
        return ResponseEntity.ok("Employee role updated to: " + role);
    }
}
