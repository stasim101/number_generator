package com.vmware.poc.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Employee.class)
public class EmployeeTest {

	@InjectMocks
	private Employee employeeMock = mock(Employee.class, Mockito.RETURNS_DEEP_STUBS);

	@Test
	public void testEmployee() throws Exception {
		PowerMockito.whenNew(Employee.class).withNoArguments().thenReturn(employeeMock);
		Employee testInstance = new Employee();
		assertEquals(employeeMock, testInstance);
	}

	@Test
	public void testEmployeeStringInt() throws Exception {
		PowerMockito.whenNew(Employee.class).withAnyArguments().thenReturn(employeeMock);
		Employee testInstance = new Employee("Test", 100);
		assertEquals(employeeMock, testInstance);
	}

	@Test
	public void testGetId() {
		when(employeeMock.getId()).thenReturn(100L);
		assertEquals(100L, employeeMock.getId());
	}

	@Test
	public void testGetEmployeeName() {
		when(employeeMock.getEmployeeName()).thenReturn("Dummy");
		assertEquals("Dummy", employeeMock.getEmployeeName());
	}

	@Test
	public void testSetEmployeeName() {
		doAnswer(invocationOnMock -> {
			String injectedString = (String) invocationOnMock.getArguments()[0];
			EmployeeTest.this.employeeMock.setEmployeeName(injectedString);
			return null;
		}).when(this.employeeMock).setEmployeeName(Mockito.anyString());
		when(this.employeeMock.getEmployeeName())
				.thenAnswer(invocationOnMock -> EmployeeTest.this.employeeMock.getEmployeeName());
	}

	@Test
	public void testGetEmployeeAge() {
		when(employeeMock.getEmployeeAge()).thenReturn(23);
		assertEquals(23, employeeMock.getEmployeeAge());
	}

	@Test
	public void testSetEmployeeAge() {
		doAnswer(invocationOnMock -> {
			int injectedInt = (int) invocationOnMock.getArguments()[0];
			EmployeeTest.this.employeeMock.setEmployeeAge(injectedInt);
			return null;
		}).when(this.employeeMock).setEmployeeAge(Mockito.anyInt());
		when(this.employeeMock.getEmployeeAge())
				.thenAnswer(invocationOnMock -> EmployeeTest.this.employeeMock.getEmployeeAge());
	}
}
