package com.jayptl.one_to_one_mapping_exp.service.imlp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jayptl.one_to_one_mapping_exp.dto.AssignDto;
import com.jayptl.one_to_one_mapping_exp.dto.EmployeeDto;
import com.jayptl.one_to_one_mapping_exp.dto.EmployeeWithoutConfInfo;
import com.jayptl.one_to_one_mapping_exp.dto.IdCardDto;
import com.jayptl.one_to_one_mapping_exp.dto.IdCardWithouConfInfo;
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
    public EmployeeDto getEmployeeById(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("\"Employee With Id " + employeeId + " Not Found"));
        if (employee.isDeleted()) {
            return new EmployeeDto();
        }
        return employeeToEmpoyeeDto(employee);
    }

    @Override
    public EmployeeDto addNewEmployee(EmployeeDto employee) {
        Employee savedEmployee = employeeRepository.save(employeeDtoToEmployee(employee));
        return employeeToEmpoyeeDto(savedEmployee);
    }

    @Override
    public boolean deleteEmployeeById(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee with Id " + employeeId + " Does Not Exists"));
        employee.setDeleted(true);
        employeeRepository.save(employee);
        return true;
    }

    @Override
    public List<EmployeeWithoutConfInfo> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> activeEmployees = new ArrayList<Employee>();
        for (Employee employee : employees) {
            if (!employee.isDeleted()) {
                activeEmployees.add(employee);
            }
        }

        List<EmployeeWithoutConfInfo> employeeDtos = new ArrayList<EmployeeWithoutConfInfo>();
        for (Employee employee : activeEmployees) {
            employeeDtos.add(emplyoeeToEmployeeWithoutConfInfo(employee));
        }
        return employeeDtos;
    }

    @Override
    public String assignIdCard(AssignDto assignDto) {
        if (employeeRepository.existsById(assignDto.getEmployeeId())) {
            if (!employeeRepository.findById(assignDto.getEmployeeId()).get().isDeleted()) {
                if (idCardRepository.existsById(assignDto.getCardId())) {
                    if (!idCardRepository.findById(assignDto.getCardId()).get().isDeleted()) {
                        IdCard idCard = idCardRepository.findById(assignDto.getCardId()).get();
                        Employee employee = employeeRepository.findById(assignDto.getEmployeeId()).get();
                        employee.setIdCard(idCard);
                        employeeRepository.save(employee);
                        return "Success";
                    }
                    return "Card With Id " + assignDto.getCardId() + " Is Deleted";
                }
                throw new EntityNotFoundException("Card With Id " + assignDto.getCardId() + " Does Not Exists");
            }
            return "Employee With Id " + assignDto.getEmployeeId() + " Is Deleted";
        }
        throw new EntityNotFoundException("Employee With Id " + assignDto.getEmployeeId() + " Does Not Exists");
    }

    private Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        if (employeeDto.getIdCardDto() != null) {
            return new Employee(0, false, employeeDto.getEmployeeName(), employeeDto.getBloodGroup(), null);
        }
        return new Employee(0, false, employeeDto.getEmployeeName(), employeeDto.getBloodGroup(), null);
    }

    private EmployeeDto employeeToEmpoyeeDto(Employee employee) {
        if (employee.getIdCard() != null) {
            IdCardDto idCardDto = new IdCardDto(employee.getIdCard().getJobTitle(),
                    employee.getIdCard().getDepartmentName(), employee.getIdCard().getConfidentialInfo());
            return new EmployeeDto(employee.getEmployeeName(), employee.getBloodGroup(), idCardDto);
        }
        return new EmployeeDto(employee.getEmployeeName(), employee.getBloodGroup(), null);
    }

    private EmployeeWithoutConfInfo emplyoeeToEmployeeWithoutConfInfo(Employee employee) {
        if (employee.getIdCard() != null) {
            IdCardWithouConfInfo idCardWithouConfInfo = new IdCardWithouConfInfo(employee.getIdCard().getJobTitle(),
                    employee.getIdCard().getDepartmentName());
            return new EmployeeWithoutConfInfo(employee.getEmployeeName(), employee.getBloodGroup(),
                    idCardWithouConfInfo);
        }
        return new EmployeeWithoutConfInfo(employee.getEmployeeName(), employee.getBloodGroup(), null);
    }

}
