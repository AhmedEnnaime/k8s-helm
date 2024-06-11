package com.learning.practice.controllers;

import com.learning.practice.models.dto.EmployeeDto;
import com.learning.practice.services.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addEmployee(@RequestBody @Valid EmployeeDto employee) {
        EmployeeDto savedEmployee = employeeService.save(employee);
        Map<String, Object> response = new HashMap<>();
        response.put("Email Sending", "success");
        response.put("Added Employee", savedEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteEmployee(@Valid @PathVariable Long id) {
        employeeService.deleteById(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee with id " + id + " was deleted successfully");
        return ResponseEntity.ok(response);
    }
}
