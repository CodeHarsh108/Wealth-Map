package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.dto.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CompanyService {
    CompanyResponseDto createCompany(CompanyRequestDto requestDto);
    CompanyResponseDto updateCompany(Long companyId, CompanyRequestDto requestDto);
    CompanyResponseDto getCompany(Long companyId);
    void deleteCompany(Long companyId);
    void updateDataAccessPreferences(Long id,CompanyDataAccessDto dataAccessDto);
    List<CompanyResponseDto> getAllCompanies();
}
