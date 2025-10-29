package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.dto.*;
import com.wealthmap.wealthmap_backend.model.Company;
import com.wealthmap.wealthmap_backend.repository.CompanyRepository;
import com.wealthmap.wealthmap_backend.repository.EmployeeRepository;
import com.wealthmap.wealthmap_backend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    public CompanyResponseDto createCompany(CompanyRequestDto requestDto) {
        Company company = modelMapper.map(requestDto, Company.class);
        company = companyRepository.save(company);
        return modelMapper.map(company, CompanyResponseDto.class);
    }

    @Override
    public CompanyResponseDto updateCompany(Long companyId, CompanyRequestDto requestDto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + companyId));

        company.setName(requestDto.getName());
        company.setDescription(requestDto.getDescription());
        company.setLogoUrl(requestDto.getLogoUrl());
        company.setWebsite(requestDto.getWebsite());
        company.setSize(requestDto.getSize());
        company.setType(requestDto.getType());

        return modelMapper.map(companyRepository.save(company), CompanyResponseDto.class);
    }

    @Override
    public CompanyResponseDto getCompany(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + companyId));
        return modelMapper.map(company, CompanyResponseDto.class);
    }

    @Override
    @Transactional
    public void deleteCompany(Long companyId) {
        // 1. Check if the company exists (optional)
        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Company not found with ID: " + companyId);
        }

        // 2. Delete all employees linked to this company
        employeeRepository.deleteEmployeesByCompanyId(companyId);

        // 3. Now safely delete the company
        companyRepository.deleteById(companyId);
    }

@Override
public void updateDataAccessPreferences(Long companyId, CompanyDataAccessDto dataAccessDto) {
    Company company = companyRepository.findById(companyId)
            .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + companyId));

    company.setDataAccessPreferences(dataAccessDto.getPreferences());
    companyRepository.save(company);
}

    @Override
    public List<CompanyResponseDto> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(company -> modelMapper.map(company, CompanyResponseDto.class))
                .collect(Collectors.toList());
    }
}
