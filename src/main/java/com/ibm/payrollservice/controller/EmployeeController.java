package com.ibm.payrollservice.controller;

import com.ibm.payrollservice.dto.EmployeeRequest;
import com.ibm.payrollservice.entity.Employee;
import com.ibm.payrollservice.exception.EmployeeNotFoundException;
import com.ibm.payrollservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @PutMapping
    public ResponseEntity<List<Employee>> updateEmployeeByName(@RequestBody @Valid EmployeeRequest employeeRequest) throws EmployeeNotFoundException {
        return new ResponseEntity<>(employeeService.updateEmployeeByName(employeeRequest), HttpStatus.OK);
    }

}
