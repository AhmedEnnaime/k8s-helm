package com.learning.practice.jobs;

import com.learning.practice.models.dto.EmployeeDto;
import com.learning.practice.services.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.flogger.Flogger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;

@Component
@Flogger
@AllArgsConstructor
public class EmployeesJob implements Job {

    private final EmployeeService employeeService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<EmployeeDto> employees = employeeService.findAll();
        log.at(Level.INFO).log("Found " + employees.size() + " employees");
        employees.forEach(employee -> log.at(Level.INFO).log(employee.toString()));
    }
}
