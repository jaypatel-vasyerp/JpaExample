package com.jayptl.one_to_one_mapping_exp.service;

import java.util.List;

import com.jayptl.one_to_one_mapping_exp.dto.AssignDto;
import com.jayptl.one_to_one_mapping_exp.model.Employee;

public interface EmployeeService {
    Employee getEmployeeById(long employeeId);

    Employee addNewEmployee(Employee employee);

    boolean deleteEmployeeById(long employeeId);

    List<Employee> getAllEmployees();

    String assignIdCard(AssignDto assignDto);

}
