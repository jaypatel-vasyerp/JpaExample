package com.jayptl.one_to_one_mapping_exp.service.imlp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayptl.one_to_one_mapping_exp.dto.AssignDto;
import com.jayptl.one_to_one_mapping_exp.exception.EntityNotFoundException;
import com.jayptl.one_to_one_mapping_exp.model.Employee;
import com.jayptl.one_to_one_mapping_exp.model.IdCard;
import com.jayptl.one_to_one_mapping_exp.repository.EmployeeRepository;
import com.jayptl.one_to_one_mapping_exp.repository.IdCardRepository;
import com.jayptl.one_to_one_mapping_exp.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private IdCardRepository idCardRepository;

    @Override
    public Employee getEmployeeById(long employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Entity With Id " + employeeId + "Not Found"));
    }

    @Override
    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean deleteEmployeeById(long employeeId) {
        employeeRepository.deleteById(employeeId);
        if (employeeRepository.existsById(employeeId)) {
            return false;
        } else
            return true;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public String assignIdCard(AssignDto assignDto) {
        if (employeeRepository.existsById(assignDto.getEmployeeId())) {
            if (idCardRepository.existsById(assignDto.getCardId())) {
                IdCard idCard = idCardRepository.findById(assignDto.getCardId()).get();
                Employee employee = employeeRepository.findById(assignDto.getEmployeeId()).get();
                employee.setIdCard(idCard);
                employeeRepository.save(employee);
                return "Success";
            }
            return "Card With Id " + assignDto.getCardId() + "Does Not Exists";
        }
        return "Employee With Id " + assignDto.getEmployeeId() + "Does Not Exists";
    }

}
