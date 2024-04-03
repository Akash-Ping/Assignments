package com.nt.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nt.dto.EmployeeInDTO;
import com.nt.dto.EmployeeOutDto;

@Service
public interface EmployeeService {

  public EmployeeOutDto saveEmployee(EmployeeInDTO employeeInDTO);
  
  public List<EmployeeOutDto> getEmployees();
}
