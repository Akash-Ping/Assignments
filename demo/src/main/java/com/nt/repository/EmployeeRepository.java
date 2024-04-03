package com.nt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.Employee;


public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
   Employee findByEmail(String email);
   
   @Query("SELECT e FROM Employee e ")
   List<Employee> getAllEmployees();
   
   
}
