package com.jayptl.one_to_one_mapping_exp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jayptl.one_to_one_mapping_exp.dto.AssignDto;
import com.jayptl.one_to_one_mapping_exp.model.Employee;
import com.jayptl.one_to_one_mapping_exp.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/id/{employeeId}")
    public Employee getEmployeeById(@PathVariable(name = "employeeId") long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/add")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        return employeeService.addNewEmployee(employee);
    }

    @PostMapping("/assignid")
    public String assignIdCard(@RequestBody AssignDto assignDto) {
        return employeeService.assignIdCard(assignDto);
    }

    @DeleteMapping("/id/{employeeId}")
    public String deleteEmployeeById(@PathVariable(name = "employeeId") long employeeId) {
        if (employeeService.deleteEmployeeById(employeeId)) {
            return "Deleted";
        } else
            return "Id Still Exists";
    }

}
