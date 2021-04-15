package com.vmware.poc.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmployeeTest {

	@Test
	void testEmployee() {
		// Custom values for test of default constructor Employee()
		Employee employeeInstance = new Employee();
		employeeInstance.setEmployeeName("employeeName");
		employeeInstance.setEmployeeAge(22);
		// Assertions Employee()
		assertTrue(employeeInstance instanceof Employee);
		assertEquals("employeeName", employeeInstance.getEmployeeName());
		assertEquals(22, employeeInstance.getEmployeeAge());
	}

	@Test
	void testEmployeeStringString() {
		// Custom values for test of parameterized constructor Employee(String
		// employeeName, String employeeAge)
		Employee employeeInstance = new Employee("employeeName", 22);
		// Assertion Employee(String employeeName, String employeeAge)
		assertTrue(employeeInstance instanceof Employee);
		assertEquals("employeeName", employeeInstance.getEmployeeName());
		assertEquals(22, employeeInstance.getEmployeeAge());
	}

	@Test
	void testGetEmployeeName() {
		// Custom values for test of retrieving an EmployeeName
		Employee employeeInstance = new Employee("employeeName", 22);
		// Assertion
		assertEquals("employeeName", employeeInstance.getEmployeeName());
	}

	@Test
	void testSetEmployeeName() {
		// Custom values for test of setting an EmployeeName
		Employee employeeInstance = new Employee();
		employeeInstance.setEmployeeName("employeeName");
		employeeInstance.setEmployeeAge(22);
		assertEquals("employeeName", employeeInstance.getEmployeeName());
	}

	@Test
	void testGetEmployeeAge() {
		// Custom values for test of retrieving an EmployeeAge
		Employee employeeInstance = new Employee("employeeName", 22);
		// Assertion
		assertEquals(22, employeeInstance.getEmployeeAge());
	}

	@Test
	void testSetEmployeeAge() {
		// Custom values for test of setting an EmployeeAge
		Employee employeeInstance = new Employee();
		employeeInstance.setEmployeeName("employeeName");
		employeeInstance.setEmployeeAge(22);
		// Assertions setEmployeeAge(int age)
		assertEquals(22, employeeInstance.getEmployeeAge());
	}

}
