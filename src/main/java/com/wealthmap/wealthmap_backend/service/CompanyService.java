package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.dto.*;

import java.util.List;

public interface CompanyService {
    CompanyResponseDto createCompany(CompanyRequestDto requestDto);
//    CompanyResponseDto updateCompany(Long companyId, CompanyRequestDto requestDto);
//    CompanyResponseDto getCompany(Long companyId);
//    void deleteCompany(Long companyId);
//    void updateDataAccessPreferences(CompanyDataAccessDto dto);
//    List<CompanyResponseDto> getAllCompanies(); // Optional if needed
}
