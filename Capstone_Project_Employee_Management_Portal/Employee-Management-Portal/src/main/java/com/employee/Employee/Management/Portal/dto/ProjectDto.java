package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class ProjectDto {
private Long id;
private String projectName;
private String description;
private String startDate;
private Long manager;
private String head;
private List<String> teamMembers;

}
