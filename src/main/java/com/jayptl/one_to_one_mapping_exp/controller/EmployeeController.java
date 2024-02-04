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
import com.jayptl.one_to_one_mapping_exp.dto.EmployeeDto;
import com.jayptl.one_to_one_mapping_exp.dto.EmployeeWithoutConfInfo;
import com.jayptl.one_to_one_mapping_exp.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public List<EmployeeWithoutConfInfo> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/id/{employeeId}")
    public EmployeeDto getEmployeeById(@PathVariable(name = "employeeId") long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @PostMapping("/add")
    public EmployeeDto addNewEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.addNewEmployee(employeeDto);
    }

    @PostMapping("/assignid")
    public String assignIdCard(@RequestBody AssignDto assignDto) {
        return employeeService.assignIdCard(assignDto);
    }

    @DeleteMapping("/delete/id/{employeeId}")
    public String deleteEmployeeById(@PathVariable(name = "employeeId") long employeeId) {
        if (employeeService.deleteEmployeeById(employeeId)) {
            return "Deleted";
        } else
            return "Id Still Exists";
    }

}
