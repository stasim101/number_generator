package com.vmware.poc.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmware.poc.model.Employee;
import com.vmware.poc.service.EmployeeService;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

	@InjectMocks
	private EmployeeController employeeController;

	@Mock
	private EmployeeService employeeService;

	@Mock
	private Employee employeeMock;

	@Autowired
	private MockMvc mvc;

	@Test
	public void testCreateNewEmployee() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		when(employeeMock.getEmployeeName()).thenReturn("Dummy");
		when(employeeMock.getEmployeeAge()).thenReturn(23);
		when(employeeService.saveAnEmployee(Mockito.any(Employee.class))).thenReturn(employeeMock);

		ResponseEntity<Employee> responseEntity = employeeController.createNewEmployee(employeeMock);

		Employee response = (Employee) responseEntity.getBody();

		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals(employeeMock.getEmployeeName(), response.getEmployeeName());
		assertEquals(employeeMock.getEmployeeAge(), response.getEmployeeAge());

		when(employeeMock.getEmployeeName()).thenReturn(null);
		responseEntity = employeeController.createNewEmployee(employeeMock);
		assertEquals(204, responseEntity.getStatusCodeValue());

	}

	@Test
	public void testRetrieveAnEmployee() {

		when(employeeMock.getEmployeeName()).thenReturn("Dummy");
		when(employeeMock.getEmployeeAge()).thenReturn(23);

		when(employeeService.getAnEmployee(Mockito.any(Long.class))).thenReturn(employeeMock);

		ResponseEntity<Employee> responseEntity = employeeController.retrieveAnEmployee(100);

		Employee response = (Employee) responseEntity.getBody();

		assertEquals(200, responseEntity.getStatusCodeValue());
		assertEquals(employeeMock.getEmployeeName(), response.getEmployeeName());
		assertEquals(employeeMock.getEmployeeAge(), response.getEmployeeAge());

		when(employeeService.getAnEmployee(1L)).thenReturn(null);
		responseEntity = employeeController.retrieveAnEmployee(1);
		assertEquals(404, responseEntity.getStatusCodeValue());

		
	}

	@Test
	public void testUpdateEmployeeData() {
		
		Employee employee = new Employee("Dummy",23);
		/*
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
				  .patch("/api/update/")
			      .contentType(MediaType.APPLICATION_JSON_VALUE)
			*/      

	}

	/*
	 * 
	 * @InjectMocks private EmployeeController employeeController;
	 * 
	 * @Mock private EmployeeService employeeService;
	 * 
	 * @Test public void testUploadEmployeeDataFile() {
	 * 
	 * }
	 * 
	 * @Test public void testCreateNewEmployee() {
	 * 
	 * MockHttpServletRequest request = new MockHttpServletRequest();
	 * RequestContextHolder.setRequestAttributes(new
	 * ServletRequestAttributes(request)); Employee testInstance = new
	 * Employee("Test user", 100);
	 * when(employeeService.saveAnEmployee(Mockito.any(Employee.class))).thenReturn(
	 * testInstance); ResponseEntity<Object> responseEntity =
	 * employeeController.createNewEmployee(testInstance); Employee response =
	 * (Employee) responseEntity.getBody();
	 * 
	 * assertEquals(201,responseEntity.getStatusCodeValue());
	 * assertEquals(testInstance.getEmployeeName(), response.getEmployeeName());
	 * assertEquals(testInstance.getEmployeeAge(), response.getEmployeeAge());
	 * 
	 * }
	 * 
	 * @Test public void testRetreiveAnEmployee() {
	 * 
	 * when(employeeService.getAnEmployee(Mockito.any(Long.class))).
	 * thenReturn("Tested perfectly"); ResponseEntity<Object> responseEntity =
	 * employeeController.retreiveAnEmployee(1L);
	 * 
	 * assertEquals("Tested perfectly",responseEntity.getBody().toString());
	 * assertEquals(200,responseEntity.getStatusCodeValue());
	 * 
	 * 
	 * }
	 * 
	 * @Test public void testUpdateEmployeeData() {
	 * 
	 * when(employeeService.updateAnEmployee(Mockito.any(Long.class),
	 * Mockito.any(Employee.class))) .thenReturn("Sample String");
	 * ResponseEntity<Object> responseEntity =
	 * employeeController.updateEmployeeData(1L,new Employee ());
	 * 
	 * assertEquals(200,responseEntity.getStatusCodeValue());
	 * assertEquals("Sample String", responseEntity.getBody().toString()); }
	 * 
	 * @Test public void testDeleteAnEmployee() {
	 * when(employeeService.deleteAnEmployee(Mockito.any(Long.class))).
	 * thenReturn("Sample String"); ResponseEntity<Object> responseEntity =
	 * employeeController.deleteAnEmployee(1L);
	 * 
	 * assertEquals(200,responseEntity.getStatusCodeValue());
	 * assertEquals("Sample String", employeeController.deleteAnEmployee(1L));
	 * 
	 * }
	 */

}
