package com.jayptl.one_to_one_mapping_exp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jayptl.one_to_one_mapping_exp.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    
}
