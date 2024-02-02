package com.jayptl.one_to_one_mapping_exp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    @JsonIgnore
    private long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "blood_group")
    private String bloodGroup;

    @OneToOne
    private IdCard idCard;

}
