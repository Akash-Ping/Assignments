package net.javaguides.springboot;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringbootBackendApplicationTests {

	private final EmployeeRepository employeeRepository;

	@Autowired
	public SpringbootBackendApplicationTests(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

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
		retrievedEmployee.ifPresent(emp -> assertEquals(savedEmployee.getId(), emp.getId()));
	}

	@Test
	public void updateEmployeeTest() {
		Employee employee = new Employee("Jane", "Smith", "jane.smith@example.com");
		Employee savedEmployee = employeeRepository.save(employee);

		savedEmployee.setFirstName("UpdatedFirstName");
		employeeRepository.save(savedEmployee);

		Optional<Employee> updatedEmployee = employeeRepository.findById(savedEmployee.getId());
		updatedEmployee.ifPresent(emp -> assertEquals("UpdatedFirstName", emp.getFirstName()));
	}

	@Test
	public void deleteEmployeeTest() {
		Employee employee = new Employee("Alex", "Johnson", "alex.johnson@example.com");
		Employee savedEmployee = employeeRepository.save(employee);

		employeeRepository.deleteById(savedEmployee.getId());

		Optional<Employee> deletedEmployee = employeeRepository.findById(savedEmployee.getId());
		assertFalse(deletedEmployee.isPresent());
	}
}
