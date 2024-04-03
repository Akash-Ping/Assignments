package com.nt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.dto.EmployeeInDTO;
import com.nt.dto.EmployeeOutDto;
import com.nt.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
  
  @Autowired
  EmployeeService employeeService;
  
  @PostMapping("/add")
  public ResponseEntity<EmployeeOutDto> saveEmployee(@RequestBody EmployeeInDTO employeeInDTO) {
    EmployeeOutDto employeeOutDto = employeeService.saveEmployee(employeeInDTO);;
    return ResponseEntity.ok(employeeOutDto);
  }
  
  @GetMapping
  public List<EmployeeOutDto> getEmployees(){
    return employeeService.getEmployees();
  }
}
