package com.jayptl.one_to_one_mapping_exp.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jayptl.one_to_one_mapping_exp.model.Employee;
import com.jayptl.one_to_one_mapping_exp.model.IdCard;
import com.jayptl.one_to_one_mapping_exp.repository.EmployeeRepository;
import com.jayptl.one_to_one_mapping_exp.repository.IdCardRepository;

@Configuration
public class SampleData {
    
    @Bean
    CommandLineRunner commandLineRunner(IdCardRepository idCardRepository,EmployeeRepository employeeRepository){
        return args ->{
            idCardRepository.save(new IdCard(0,false,"Software Developer Intern","Development","Confidential information"));
            employeeRepository.save(new Employee(0,false,"Jay Patel","O +",null));
        };
    }

}
