package com.nt.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.nt.entity.Employee;

public class EmployeeInDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "emp_seq")
  private int emp_id;
  
  @Column(nullable = false)
  private String name;
  
  @Column(unique = true, nullable = false)
  private String email;
  
  @Column(nullable = false)
  private String password;

  /**
   * @return the emp_id
   */
  public int getEmp_id() {
    return emp_id;
  }

  /**
   * @param emp_id the emp_id to set
   */
  public void setEmp_id(int emp_id) {
    this.emp_id = emp_id;
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }
  
  public Employee InDtoToEmployee() {
    Employee employee = new Employee();
    employee.setEmail(this.email);
    employee.setEmp_id(this.emp_id);
    employee.setName(this.name);
    employee.setPassword(this.password);
    return employee;
  }
}
