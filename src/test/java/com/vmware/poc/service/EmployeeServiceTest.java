package com.vmware.poc.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.RETURNS_DEEP_STUBS;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.vmware.poc.model.Employee;
import com.vmware.poc.repository.EmployeeRepository;

@RunWith(PowerMockRunner.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService = mock(EmployeeService.class, RETURNS_DEEP_STUBS);
	@Mock
	private EmployeeRepository employeeRepository;

	@Test
	public void testSaveAnEmployee() {
		Employee testInstance = new Employee("Test user", 100);
		when(employeeService.saveAnEmployee(Mockito.any(Employee.class))).thenReturn(testInstance);
		Employee saved = employeeService.saveAnEmployee(testInstance);
		assertEquals(testInstance.getEmployeeName(), saved.getEmployeeName());
	}

	@Test
	public void testGetAnEmployee() {
		when(employeeService.getAnEmployee(Mockito.any(Long.class))).thenReturn("Sample String");
		assertEquals("Sample String", employeeService.getAnEmployee(1L));
	}

	@Test
	public void testUpdateAnEmployee() {
		when(employeeService.updateAnEmployee(Mockito.any(Long.class), Mockito.any(Employee.class)))
				.thenReturn("Sample String");
		assertEquals("Sample String", employeeService.updateAnEmployee(1L, new Employee()));
	}

	@Test
	public void testDeleteAnEmployee() {
		when(employeeService.deleteAnEmployee(Mockito.any(Long.class))).thenReturn("Sample String");
		assertEquals("Sample String", employeeService.deleteAnEmployee(1L));
	}

}
