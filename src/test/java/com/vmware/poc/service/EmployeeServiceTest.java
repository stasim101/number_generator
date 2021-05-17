package com.vmware.poc.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.vmware.poc.model.Employee;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EmployeeService.class)
public class EmployeeServiceTest {

	@InjectMocks
	private EmployeeService employeeServiceMock = mock(EmployeeService.class, Mockito.RETURNS_DEEP_STUBS);

	@Mock
	private Employee employeeMock;

	@Test(expected = IllegalArgumentException.class)
	public void testSaveAnEmployee() {
		doThrow(IllegalArgumentException.class).when(employeeServiceMock).saveAnEmployee(null);

		when(employeeServiceMock.saveAnEmployee(Mockito.any(Employee.class))).thenReturn(employeeMock);
		when(employeeMock.getEmployeeName()).thenReturn("Dummy");
		when(employeeMock.getEmployeeAge()).thenReturn(23);
		when(employeeMock.getId()).thenReturn(10L);

		Employee actual = employeeServiceMock.saveAnEmployee(employeeMock);

		assertEquals(employeeMock.getId(), actual.getId());
		assertEquals(employeeMock.getEmployeeName(), actual.getEmployeeName());
		assertEquals(employeeMock.getEmployeeAge(), actual.getEmployeeAge());

		employeeServiceMock.saveAnEmployee(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetAnEmployee() {

		doThrow(IllegalArgumentException.class).when(employeeServiceMock).getAnEmployee(0);

		when(employeeServiceMock.getAnEmployee(Mockito.anyLong())).thenReturn(employeeMock);
		when(employeeMock.getEmployeeName()).thenReturn("Dummy");
		when(employeeMock.getEmployeeAge()).thenReturn(23);
		when(employeeMock.getId()).thenReturn(10L);

		Employee actual = employeeServiceMock.getAnEmployee(10L);

		assertEquals(employeeMock.getId(), actual.getId());
		assertEquals(employeeMock.getEmployeeName(), actual.getEmployeeName());
		assertEquals(employeeMock.getEmployeeAge(), actual.getEmployeeAge());

		employeeServiceMock.getAnEmployee(0);
	}

	@Test
	public void testUpdateAnEmployee() {

		when(employeeServiceMock.updateAnEmployee(Mockito.anyLong(), Mockito.any(Employee.class)))
				.thenReturn(employeeMock);

		when(employeeMock.getEmployeeName()).thenReturn("Dummy");
		when(employeeMock.getEmployeeAge()).thenReturn(23);
		when(employeeMock.getId()).thenReturn(10L);

		Employee actual = employeeServiceMock.updateAnEmployee(10L, employeeMock);

		assertEquals(employeeMock.getId(), actual.getId());
		assertEquals(employeeMock.getEmployeeName(), actual.getEmployeeName());
		assertEquals(employeeMock.getEmployeeAge(), actual.getEmployeeAge());

	}

	@Test
	public void testdeleteAnEmployee() {

		when(employeeServiceMock.deleteAnEmployee(Mockito.anyLong())).thenReturn(true);
		assertTrue(employeeServiceMock.deleteAnEmployee(10));

		when(employeeServiceMock.deleteAnEmployee(Mockito.anyLong())).thenReturn(false);
		assertTrue(!employeeServiceMock.deleteAnEmployee(10));

	}

	@Test
	public void testGetEmployeeList() {

		when(employeeServiceMock.saveAnEmployee(Mockito.any(Employee.class))).thenReturn(employeeMock);
		when(employeeMock.getEmployeeName()).thenReturn("Dummy");
		when(employeeMock.getEmployeeAge()).thenReturn(23);
		when(employeeMock.getId()).thenReturn(10L);
		List<Employee> employeeList = new ArrayList();
		employeeList.add(employeeMock);
		when(employeeServiceMock.getEmployeeList()).thenReturn(employeeList);
		Employee actual = employeeServiceMock.getEmployeeList().get(0);

		assertEquals(employeeMock.getId(), actual.getId());
		assertEquals(employeeMock.getEmployeeName(), actual.getEmployeeName());
		assertEquals(employeeMock.getEmployeeAge(), actual.getEmployeeAge());

	}
	
}
