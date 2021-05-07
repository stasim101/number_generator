package com.vmware.poc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.vmware.poc.model.Employee;
import com.vmware.poc.repository.EmployeeRepository;
import com.vmware.poc.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private Employee employee;

	@Mock
	private EmployeeRepository employeeRepository;

	//@Test
	public void testUploadEmployeeDataFile() {

	}

	@Test
	public void testCreateNewEmployee() throws Exception {

		when(employee.getEmployeeName()).thenReturn("Testname");
		when(employee.getEmployeeAge()).thenReturn(10);
		when(employeeService.saveAnEmployee(employee)).thenReturn(employee);

		ResponseEntity<Employee> responseEntity = employeeController.createNewEmployee(employee);
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals("Testname", responseEntity.getBody().getEmployeeName());
		assertEquals(10, responseEntity.getBody().getEmployeeAge());

		when(employee.getEmployeeName()).thenReturn(null);
		responseEntity = employeeController.createNewEmployee(employee);
		assertEquals(204, responseEntity.getStatusCodeValue());
	}

	@Test
	public void testRetrieveAnEmployee() {

		when(employee.getEmployeeName()).thenReturn("Testname");
		when(employee.getEmployeeAge()).thenReturn(10);
		when(employeeService.getAnEmployee(Mockito.anyLong())).thenReturn(employee);

		ResponseEntity<Employee> responseEntity = employeeController.retrieveAnEmployee(1000L);
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals("Testname", responseEntity.getBody().getEmployeeName());
		assertEquals(10, responseEntity.getBody().getEmployeeAge());

	}

	@Test
	public void testUpdateEmployeeData() {

		when(employee.getEmployeeName()).thenReturn("Testname");
		when(employee.getEmployeeAge()).thenReturn(10);
		when(employeeService.getAnEmployee(Mockito.anyLong())).thenReturn(employee);
		when(employeeService.updateAnEmployee(Mockito.anyLong(), Mockito.any(Employee.class))).thenReturn(employee);

		ResponseEntity<Employee> responseEntity = employeeController.updateEmployeeData(10L, employee);
		assertEquals("Testname", responseEntity.getBody().getEmployeeName());
		assertEquals(10, responseEntity.getBody().getEmployeeAge());
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	public void testDeleteEmployeeData() {

		when(employeeService.getAnEmployee(Mockito.anyLong())).thenReturn(null);
		ResponseEntity<Object> responseEntity = employeeController.deleteEmployeeData(10L);
		assertEquals(false, responseEntity.getBody());
		assertEquals(200, responseEntity.getStatusCodeValue());

		when(employeeService.deleteAnEmployee(Mockito.anyLong())).thenReturn(true);
		responseEntity = employeeController.deleteEmployeeData(10L);
		assertEquals(true, responseEntity.getBody());
		assertEquals(200, responseEntity.getStatusCodeValue());

	}

	@Test
	public void testGetAllEmployees() {

		List<Employee> list = new ArrayList<>();
		list.add(new Employee("User1", 10));
		list.add(new Employee("User2", 20));

		when(employeeService.getEmployeeList()).thenReturn(list);
		ResponseEntity<Object> responseEntity = employeeController.getAllEmployees();
		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals(ArrayList.class, responseEntity.getBody().getClass());

	}

}
