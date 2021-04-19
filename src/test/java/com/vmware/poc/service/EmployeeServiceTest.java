package com.vmware.poc.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.vmware.poc.model.Employee;
import com.vmware.poc.repository.EmployeeRepository;

@RunWith(PowerMockRunner.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeService;
	@Mock
	private EmployeeRepository employeeRepository;

	@Test
	public void testSaveAnEmployee() {
		
		Employee testInstance = new Employee("Test user",100);
		
		
		
		
	}

	@Test
	public void testGetAnEmployee() {

	}

	@Test
	public void testUpdateAnEmployee() {

	}

	@Test
	public void testDeleteAnEmployee() {

	}

}
