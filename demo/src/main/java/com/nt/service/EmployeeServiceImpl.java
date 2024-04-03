package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dto.EmployeeInDTO;
import com.nt.dto.EmployeeOutDto;
import com.nt.entity.Employee;
import com.nt.exception.CustomException;
import com.nt.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  
  @Autowired
  EmployeeRepository employeeRepository;

  @Override
  public EmployeeOutDto saveEmployee(EmployeeInDTO employeeInDTO) {
    Employee employee = employeeInDTO.InDtoToEmployee();
    
    Employee existingEmployee = employeeRepository.findByEmail(employeeInDTO.getEmail());
    if(!Objects.isNull(existingEmployee)) {
      throw new CustomException("Employee with this email already exist.");
    }
    Employee emp = employeeRepository.save(employee);
    return emp.employeeToOutDto();
  }
  
  @Override
  public List<EmployeeOutDto> getEmployees() {
    List<Employee> employees = employeeRepository.getAllEmployees();
    List<EmployeeOutDto> employeeOutDtos = new ArrayList<EmployeeOutDto>();
    for(Employee emp : employees) {
      EmployeeOutDto employeeOutDto = emp.employeeToOutDto();
      employeeOutDtos.add(employeeOutDto);
    }
    return employeeOutDtos;
  }
  
  
  

}
