package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.entity.Skills;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterOutDto {
    private Long id;
    private String name;
    private String contactNo;
    private String dob;
    private String doj;
    private String designation;
    private String email;
    private String empId;
    private String location;
//    private String password;
    private Role role;
    private Long EmpProjectId;

    private String ManagerName;

    private String ProjectName;

    private Set<Skills> assignedSkills;



}
