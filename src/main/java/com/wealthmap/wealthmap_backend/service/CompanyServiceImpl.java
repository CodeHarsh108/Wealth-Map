package com.wealthmap.wealthmap_backend.service;

import com.wealthmap.wealthmap_backend.dto.*;
import com.wealthmap.wealthmap_backend.model.Company;
import com.wealthmap.wealthmap_backend.repository.CompanyRepository;
import com.wealthmap.wealthmap_backend.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    @Override
    public CompanyResponseDto createCompany(CompanyRequestDto requestDto) {
        Company company = modelMapper.map(requestDto, Company.class);
        company = companyRepository.save(company);
        return modelMapper.map(company, CompanyResponseDto.class);
    }

    @Override
    public CompanyResponseDto updateCompany(Long companyId, CompanyRequestDto requestDto) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

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
                .orElseThrow(() -> new RuntimeException("Company not found"));
        return modelMapper.map(company, CompanyResponseDto.class);
    }

    @Override
    public void deleteCompany(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new RuntimeException("Company not found");
        }
        companyRepository.deleteById(companyId);
    }

@Override
public void updateDataAccessPreferences(Long id, CompanyDataAccessDto dataAccessDto) {
    Company company = companyRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Company not found"));

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
