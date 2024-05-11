package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.Skills;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UpdateSkillDto {

    private String email;

    private Set<Skills> skills;
}
