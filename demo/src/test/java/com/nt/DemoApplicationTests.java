package com.nt;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nt.entity.Employee;
import com.nt.repository.EmployeeRepository;

@SpringBootTest
class DemoApplicationTests {
  
  @Test
  void contextLoads() {
  }
  
  @Autowired
  EmployeeRepository employeeRepository;
  
  @Test
  public void addEmployeeTest() {
    Employee employee = new Employee("Sagar Kalthiya", "sagar124@nucleusteq.com", "Sagar123");
    Employee savedEmployee = employeeRepository.saveAndFlush(employee);
    assertEquals(employee, savedEmployee);
  }

  @Test
  public void addEmployeeeTest() {
    Employee employee = new Employee("Sagar Kalthiya", "sagar124@nucleusteq.com", "Sagar123");
    Employee savedEmployee = employeeRepository.saveAndFlush(employee);
    assertEquals(employee, savedEmployee);
  }
  
}
