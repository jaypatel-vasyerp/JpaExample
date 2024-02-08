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
import com.jayptl.one_to_one_mapping_exp.dto.ResponseDto;
import com.jayptl.one_to_one_mapping_exp.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/all")
    public ResponseDto getAllEmployees() {
        List<EmployeeWithoutConfInfo> data = employeeService.getAllEmployees();
        ResponseDto responseDto = new ResponseDto();

        responseDto.setStatus(200);
        responseDto.setMessage("Ok");
        responseDto.setData(data);

        return responseDto;
    }

    @GetMapping("/id/{employeeId}")
    public ResponseDto getEmployeeById(@PathVariable(name = "employeeId") long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        ResponseDto responseDto = new ResponseDto();
        if (employeeDto != null) {
            responseDto.setStatus(200);
            responseDto.setMessage("Ok");
            responseDto.setData(employeeDto);
        } else {

            // if employee's isDeleted flag is true

            responseDto.setStatus(404);
            responseDto.setMessage("Emplyoee is Deleted");
            responseDto.setData(employeeDto);
        }
        return responseDto;
    }

    @PostMapping("/add")
    public ResponseDto addNewEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto employee = employeeService.addNewEmployee(employeeDto);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Ok");
        responseDto.setStatus(200);
        responseDto.setData(employee);
        return responseDto;
    }

    @PostMapping("/assignid")
    public ResponseDto assignIdCard(@RequestBody AssignDto assignDto) {
        String data = employeeService.assignIdCard(assignDto);
        ResponseDto responseDto = new ResponseDto();
        if (data.equals("Success")) {
            responseDto.setStatus(200);
            responseDto.setMessage("Ok");
            responseDto.setData(data);
        } else {

            // if idcard's or employee's isDeleted flag is true then
            responseDto.setStatus(404);
            responseDto.setMessage("Error");
            responseDto.setData(data);
        }
        return responseDto;
    }

    @DeleteMapping("/delete/id/{employeeId}")
    public ResponseDto deleteEmployeeById(@PathVariable(name = "employeeId") long employeeId) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage("Ok");
        responseDto.setStatus(200);
        responseDto.setData(employeeService.deleteEmployeeById(employeeId));
        return responseDto;
    }

}
