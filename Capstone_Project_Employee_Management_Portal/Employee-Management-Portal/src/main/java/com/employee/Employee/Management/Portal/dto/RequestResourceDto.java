package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.Project;
import lombok.Data;

@Data
public class RequestResourceDto {

    private String empId;

    private String email;

    private Long projectId;

    private String comment;

}
