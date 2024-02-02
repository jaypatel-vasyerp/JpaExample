package com.jayptl.one_to_one_mapping_exp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignDto {
    
    private long employeeId;
    private long cardId;
}
