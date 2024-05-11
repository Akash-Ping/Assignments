package com.employee.Employee.Management.Portal.dto;

import com.employee.Employee.Management.Portal.entity.Role;
import com.employee.Employee.Management.Portal.entity.Skills;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class RegisterDto {

    private Long id;
    private String name;
    private String contactNo;
    private String dob;
    private String doj;
    private String designation;
    private String email;
    private String empId;
    private String location;
    private String password;
    private Role role;
    private Long EmpManagerId;
    private Long EmpProjectId;

    private String ManagerName;

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    private String ProjectName;

    private Set<Long> assignedSkills;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getEmpManagerId() {
        return EmpManagerId;
    }

    public void setEmpManagerId(Long empManagerId) {
        EmpManagerId = empManagerId;
    }

    public Long getEmpProjectId() {
        return EmpProjectId;
    }

    public void setEmpProjectId(Long empProjectId) {
        EmpProjectId = empProjectId;
    }

    public Set<Long> getAssignedSkills() {
        return assignedSkills;
    }

    public void setAssignedSkills(Set<Long> assignedSkills) {
        this.assignedSkills = assignedSkills;
    }

    public String getManagerName() {
        return ManagerName;
    }

    public void setManagerName(String ManagerName) {
        this.ManagerName = ManagerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegisterDto that = (RegisterDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(contactNo, that.contactNo) && Objects.equals(dob, that.dob) && Objects.equals(doj, that.doj) && Objects.equals(designation, that.designation) && Objects.equals(email, that.email) && Objects.equals(empId, that.empId) && Objects.equals(location, that.location) && Objects.equals(password, that.password) && role == that.role && Objects.equals(EmpManagerId, that.EmpManagerId) && Objects.equals(EmpProjectId, that.EmpProjectId) && Objects.equals(ManagerName, that.ManagerName) && Objects.equals(ProjectName, that.ProjectName) && Objects.equals(assignedSkills, that.assignedSkills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contactNo, dob, doj, designation, email, empId, location, password, role, EmpManagerId, EmpProjectId, ManagerName, ProjectName, assignedSkills);
    }

    @Override
    public String toString() {
        return "RegisterDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", dob='" + dob + '\'' +
                ", doj='" + doj + '\'' +
                ", designation='" + designation + '\'' +
                ", email='" + email + '\'' +
                ", empId='" + empId + '\'' +
                ", location='" + location + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", EmpManagerId=" + EmpManagerId +
                ", EmpProjectId=" + EmpProjectId +
                ", ManagerName='" + ManagerName + '\'' +
                ", ProjectName='" + ProjectName + '\'' +
                ", assignedSkills=" + assignedSkills +
                '}';
    }
}