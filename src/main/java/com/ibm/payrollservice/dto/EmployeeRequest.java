package com.ibm.payrollservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor (staticName = "build")
@NoArgsConstructor
public class EmployeeRequest {

    @NotNull(message = "employee name can not be null")
    private String name;

    @NotNull(message = "role can not be null")
    private String role;
}
