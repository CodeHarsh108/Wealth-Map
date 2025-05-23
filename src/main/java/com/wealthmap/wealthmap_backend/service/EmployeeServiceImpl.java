//package com.wealthmap.wealthmap_backend.service.impl;
//
//import com.wealthmap.wealthmap_backend.dto.EmployeeRequestDto;
//import com.wealthmap.wealthmap_backend.dto.EmployeeResponseDto;
//import com.wealthmap.wealthmap_backend.model.Company;
//import com.wealthmap.wealthmap_backend.model.Employee;
//import com.wealthmap.wealthmap_backend.repository.CompanyRepository;
//import com.wealthmap.wealthmap_backend.repository.EmployeeRepository;
//import com.wealthmap.wealthmap_backend.service.EmployeeService;
//import lombok.RequiredArgsConstructor;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class EmployeeServiceImpl implements EmployeeService {
//
//    private final EmployeeRepository employeeRepository;
//    private final CompanyRepository companyRepository;
//    private final ModelMapper modelMapper;
//
//    @Override
//    public EmployeeResponseDto inviteEmployee(Long companyId, EmployeeRequestDto requestDto) {
//        Company company = companyRepository.findById(companyId)
//                .orElseThrow(() -> new RuntimeException("Company not found"));
//
//        Employee employee = modelMapper.map(requestDto, Employee.class);
//        employee.setCompany(company);
//        employee.setJoinedAt(LocalDateTime.now());
//        employee.setActive(true);
//
//        employee = employeeRepository.save(employee);
//        return modelMapper.map(employee, EmployeeResponseDto.class);
//    }
//
//    @Override
//    public List<EmployeeResponseDto> getEmployeesByCompany(Long companyId) {
//        return employeeRepository.findByCompanyId(companyId)
//                .stream()
//                .map(emp -> modelMapper.map(emp, EmployeeResponseDto.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public EmployeeResponseDto updateEmployeeRole(Long employeeId, EmployeeRequestDto requestDto) {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//
//        employee.setRole(requestDto.getRole());
//        return modelMapper.map(employeeRepository.save(employee), EmployeeResponseDto.class);
//    }
//
//    @Override
//    public void deactivateEmployee(Long employeeId) {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//        employee.setActive(false);
//        employee.setLastActiveAt(LocalDateTime.now());
//        employeeRepository.save(employee);
//    }
//
//    @Override
//    public EmployeeResponseDto getEmployee(Long employeeId) {
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//        return modelMapper.map(employee, EmployeeResponseDto.class);
//    }
//}
