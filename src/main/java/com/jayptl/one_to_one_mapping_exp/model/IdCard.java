package com.jayptl.one_to_one_mapping_exp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdCard {

    @Id
    @GeneratedValue()
    @Column(name = "card_id")
    private long cardId;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @Column(name = "job_title")
    private String jobTitle;
    
    @Column(name = "department_name")
    private String departmentName;

    @Column(name = "confidential_info")
    private String confidentialInfo;

}
