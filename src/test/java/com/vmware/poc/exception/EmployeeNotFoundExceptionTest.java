package com.vmware.poc.exception;

import org.junit.Test;

public class EmployeeNotFoundExceptionTest {

	@Test(expected=EmployeeNotFoundException.class)
	public void testEmployeeNotFoundException() {
		throw new EmployeeNotFoundException();
	}

}
