package com.vmware.poc.exception;

import org.junit.Test;

public class TaskNotFoundExceptionTest {

	@Test(expected = TaskNotFoundException.class)
	public void testTaskNotFoundException() {
		throw new TaskNotFoundException();
	}

}
