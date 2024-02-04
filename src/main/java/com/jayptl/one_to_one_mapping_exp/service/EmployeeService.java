package com.jayptl.one_to_one_mapping_exp.service;

import java.util.List;

import com.jayptl.one_to_one_mapping_exp.dto.AssignDto;
import com.jayptl.one_to_one_mapping_exp.dto.EmployeeDto;
import com.jayptl.one_to_one_mapping_exp.dto.EmployeeWithoutConfInfo;

public interface EmployeeService {
    EmployeeDto getEmployeeById(long employeeId);

    EmployeeDto addNewEmployee(EmployeeDto employeeDto);

    boolean deleteEmployeeById(long employeeId);

    List<EmployeeWithoutConfInfo> getAllEmployees();

    String assignIdCard(AssignDto assignDto);

}
