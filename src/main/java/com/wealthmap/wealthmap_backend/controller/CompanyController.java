package com.wealthmap.wealthmap_backend.controller;

import com.wealthmap.wealthmap_backend.dto.CompanyDataAccessDto;
import com.wealthmap.wealthmap_backend.dto.CompanyRequestDto;
import com.wealthmap.wealthmap_backend.dto.CompanyResponseDto;
import com.wealthmap.wealthmap_backend.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDto> registerCompany(@Valid @RequestBody CompanyRequestDto dto) {
        CompanyResponseDto created = companyService.createCompany(dto);
        return ResponseEntity.ok(created);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<CompanyResponseDto> getCompany(@PathVariable Long id) {
//        return ResponseEntity.ok(companyService.getCompanyById(id));
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<CompanyResponseDto> updateCompany(@PathVariable Long id, @Valid @RequestBody CompanyRequestDto dto) {
//        return ResponseEntity.ok(companyService.updateCompany(id, dto));
//    }
//
//    @PutMapping("/{id}/data-access")
//    public ResponseEntity<Void> updateDataAccessPrefs(@PathVariable Long id, @RequestBody CompanyDataAccessDto dataAccessDto) {
//        companyService.updateDataAccessPreferences(id, dataAccessDto.getPreferences());
//        return ResponseEntity.ok().build();
//    }
}
