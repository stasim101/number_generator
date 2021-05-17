package com.vmware.poc.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.vmware.poc.enums.UploadTaskStatus;
import com.vmware.poc.model.UploadTask;

public class UploadTaskServiceTest {

	@InjectMocks
	private UploadTaskService uploadTaskService = mock(UploadTaskService.class, Mockito.RETURNS_DEEP_STUBS);

	@Mock
	private UploadTask uploadTask;

	@Test
	public void testGenerateTask() {
		when(uploadTaskService.generateTask(Mockito.any(String.class))).thenReturn(uploadTask);
		assertEquals(uploadTask, uploadTaskService.generateTask("Test"));
	}

	@Test
	public void testGetTaskStatusById() {
		when(uploadTaskService.getTaskStatusById(Mockito.anyLong())).thenReturn(UploadTaskStatus.SUCCESS);
		assertEquals(UploadTaskStatus.SUCCESS, uploadTaskService.getTaskStatusById(100L));
	}

	@Test
	public void testSetTaskStatusById() {

		when(uploadTaskService.setTaskStatusById(Mockito.anyLong(), Mockito.any(UploadTaskStatus.class)))
				.thenReturn(UploadTaskStatus.SUCCESS);
		assertEquals(UploadTaskStatus.SUCCESS, uploadTaskService.setTaskStatusById(100L, UploadTaskStatus.PROCESSING));
	}

	@Test
	public void testGetTaskById() {
		when(uploadTaskService.getTaskById(Mockito.anyLong())).thenReturn(uploadTask);
		assertEquals(uploadTask, uploadTaskService.getTaskById(100L));
	}

}
