package com.ibm.payrollservice.service;

import com.ibm.payrollservice.dto.EmployeeRequest;
import com.ibm.payrollservice.entity.Employee;
import com.ibm.payrollservice.exception.EmployeeNotFoundException;
import com.ibm.payrollservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.build(0, employeeRequest.getName(), employeeRequest.getRole());
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(int id) throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee;
        } else {
            throw new EmployeeNotFoundException("Employee not found with id:" + id);
        }
    }

    public List<Employee> updateEmployeeByName(EmployeeRequest employeeRequest) throws EmployeeNotFoundException {
        List<Employee> employees = employeeRepository.findByName(employeeRequest.getName());
        List<Employee> updatedEmployees = new ArrayList<>();
        if (employees.size() != 0) {
            for (Employee employee : employees) {
                Employee updatedemployee = Employee.build(employee.getId(), employee.getName(), employeeRequest.getRole());
                employeeRepository.save(updatedemployee);
                updatedEmployees.add(updatedemployee);
            }
        } else {
            throw new EmployeeNotFoundException("No Employee not found with name: " + employeeRequest.getName());
        }
        return updatedEmployees;
    }
}
