package net.javaguides.springboot;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SpringbootBackendApplicationTests {

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	public void addEmployeeTest() {
		Employee employee = new Employee("Sagar", "Kalthiya", "Sagar123@gmail.com");
		Employee savedEmployee = employeeRepository.saveAndFlush(employee);
		assertEquals(employee, savedEmployee);
	}

	@Test
	public void getEmployeeByIdTest() {
		Employee employee = new Employee("John", "Doe", "john.doe@example.com");
		Employee savedEmployee = employeeRepository.save(employee);

		Optional<Employee> retrievedEmployee = employeeRepository.findById(savedEmployee.getId());
		assertTrue(retrievedEmployee.isPresent());
		assertEquals(savedEmployee.getId(), retrievedEmployee.get().getId());
	}

	@Test
	public void updateEmployeeTest() {
		Employee employee = new Employee("Jane", "Smith", "jane.smith@example.com");
		Employee savedEmployee = employeeRepository.save(employee);

		savedEmployee.setFirstName("UpdatedFirstName");
		employeeRepository.save(savedEmployee);

		Optional<Employee> updatedEmployee = employeeRepository.findById(savedEmployee.getId());
		assertTrue(updatedEmployee.isPresent());
		assertEquals("UpdatedFirstName", updatedEmployee.get().getFirstName());
	}

	@Test
	public void deleteEmployeeTest() {
		Employee employee = new Employee("Alex", "Johnson", "alex.johnson@example.com");
		Employee savedEmployee = employeeRepository.save(employee);

		employeeRepository.deleteById(savedEmployee.getId());

		Optional<Employee> deletedEmployee = employeeRepository.findById(savedEmployee.getId());
		assertTrue(deletedEmployee.isEmpty());
	}
}
