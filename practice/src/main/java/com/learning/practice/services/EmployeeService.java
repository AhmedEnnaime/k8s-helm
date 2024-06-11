package com.learning.practice.services;

import com.learning.practice.models.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll();
    EmployeeDto save(EmployeeDto employeeDto);
    EmployeeDto findById(Long id);
    void deleteById(Long id);
}
