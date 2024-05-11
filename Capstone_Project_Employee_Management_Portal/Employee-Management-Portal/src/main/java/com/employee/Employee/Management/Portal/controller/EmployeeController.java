package com.employee.Employee.Management.Portal.controller;

import com.employee.Employee.Management.Portal.dto.ApiResponseDto;
import com.employee.Employee.Management.Portal.dto.RegisterDto;
import com.employee.Employee.Management.Portal.dto.UpdateSkillDto;
import com.employee.Employee.Management.Portal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/{email}")
    public RegisterDto getEmployeeByEmail(@PathVariable final String email) {
        RegisterDto registerDto = employeeService.getEmployee(email);
        return registerDto;
    }


    @PostMapping("/updateskills")
    public ApiResponseDto updateSkillsOfEmployee(
             @RequestBody final UpdateSkillDto updateSkillDto) {
        ApiResponseDto apiResponseDto = employeeService
                .updateSkill(updateSkillDto);
        return apiResponseDto;
    }


}
