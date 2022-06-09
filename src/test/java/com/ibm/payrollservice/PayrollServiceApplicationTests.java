package com.ibm.payrollservice;

import com.ibm.payrollservice.dto.EmployeeRequest;
import com.ibm.payrollservice.entity.Employee;
import com.ibm.payrollservice.repository.EmployeeRepository;
import com.ibm.payrollservice.service.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class PayrollServiceApplicationTests {

    private String baseUrl = "http://localhost:8080/employee";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository mockedRepository;

    public static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @Test
    void testAddEmployee() throws URISyntaxException {
        EmployeeRequest employeeRequest = EmployeeRequest.build("mesut", "developer");
        Employee response = restTemplate.postForObject(new URI(baseUrl), employeeRequest, Employee.class);
        assertEquals("mesut", response.getName());
        assertEquals("developer", response.getRole());
    }

    @Test
    void testUpdateByName() throws URISyntaxException {
        EmployeeRequest employeeRequest = EmployeeRequest.build("mesut", "developer");
        restTemplate.postForObject(new URI(baseUrl), employeeRequest, Employee.class);
        employeeRequest = EmployeeRequest.build("mesut", "developer");
        restTemplate.postForObject(new URI(baseUrl), employeeRequest, Employee.class);
        employeeRequest = EmployeeRequest.build("mesut", "senior");
        restTemplate.put(new URI(baseUrl), employeeRequest);
        List<Employee> employeeList = employeeRepository.findByName(employeeRequest.getName());

        for (Employee employee : employeeList) {
            assertEquals("senior", employee.getName());
        }
    }

    @Test
    void testGetAllEmployeesService(){
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = Employee.build(0,"mesut", "developer");
        employeeList.add(employee);
        employeeList.add(employee);

        when(mockedRepository.findAll()).thenReturn(employeeList);
        assertEquals(2,employeeService.getAllEmployees().size());
    }

}
