package com.ibm.payrollservice.exception;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String message){
        super(message);
    }
}
