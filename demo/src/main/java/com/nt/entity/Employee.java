package com.nt.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.nt.dto.EmployeeInDTO;
import com.nt.dto.EmployeeOutDto;

//A simple Employee POJO
@Entity
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "emp_seq")
  private int emp_id;

  @Column
  private String name;

  @Column(unique = true)
  private String email;

  @Column
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

  @Override
  public String toString() {
    return "Employee [emp_id=" + emp_id + ", name=" + name
        + ", email=" + email + ", password=" + password + "]";
  }

  public Employee() {
    super();
  }

  public Employee(String name, String email,
      String password) {
    super();
    this.name = name;
    this.email = email;
    this.password = password;
  }

  public EmployeeInDTO employeeToInDto() {
    EmployeeInDTO employeeInDTO = new EmployeeInDTO();
    employeeInDTO.setEmail(email);
    employeeInDTO.setEmp_id(emp_id);
    employeeInDTO.setName(name);
    employeeInDTO.setPassword(password);
    return employeeInDTO;
  }

  public EmployeeOutDto employeeToOutDto() {
    EmployeeOutDto employeeOutDto = new EmployeeOutDto();
    employeeOutDto.setEmail(email);
    employeeOutDto.setName(name);
    return employeeOutDto;
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, name, password);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Employee other = (Employee) obj;
    return Objects.equals(email, other.email)
        && Objects.equals(name, other.name)
        && Objects.equals(password, other.password);
  }

}
