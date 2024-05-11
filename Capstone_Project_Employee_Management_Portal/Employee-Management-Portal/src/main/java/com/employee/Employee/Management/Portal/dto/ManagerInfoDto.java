package com.employee.Employee.Management.Portal.dto;

import lombok.Data;

@Data
public class ManagerInfoDto {

    private Long id;
    private String managerName;
    private String managerEmployeeId;

    public ManagerInfoDto() {

    }
}
