package com.learning.practice.services.impl;

import com.learning.practice.exceptions.ResourceNotFoundException;
import com.learning.practice.models.dto.EmployeeDto;
import com.learning.practice.models.entities.Employee;
import com.learning.practice.repositories.EmployeeRepository;
import com.learning.practice.services.EmailService;
import com.learning.practice.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final EmailService emailService;
    private final EmailServiceImpl emailServiceImpl;

    @Override
    public List<EmployeeDto> findAll() {
        return Arrays.asList(modelMapper.map(employeeRepository.findAll(), EmployeeDto[].class));
    }

    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto, Employee.class);
        Employee savedEmployee = employeeRepository.save(employee);
        emailServiceImpl.sendEmail("ahmedennaime36@gmail.com", "New Employee Joined", "A new employee Joined with the name " + savedEmployee.getFirstName() + " " + savedEmployee.getLastName());
        return modelMapper.map(savedEmployee, EmployeeDto.class);
    }

    @Override
    public EmployeeDto findById(Long id) {
        if (!employeeRepository.existsById(id))
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        return modelMapper.map(employeeRepository.findById(id), EmployeeDto.class);
    }

    @Override
    public void deleteById(Long id) {
        if (!employeeRepository.existsById(id))
            throw new ResourceNotFoundException("Employee with id " + id + " not found");
        employeeRepository.deleteById(id);
    }
}
