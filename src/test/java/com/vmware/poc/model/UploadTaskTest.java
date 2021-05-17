package com.vmware.poc.model;

import static org.junit.Assert.assertEquals;
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

import com.vmware.poc.enums.UploadTaskStatus;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UploadTask.class)
public class UploadTaskTest {

	@InjectMocks
	private UploadTask uploadTaskMock = mock(UploadTask.class, Mockito.RETURNS_DEEP_STUBS);

	@Test
	public void testUploadTask() throws Exception {
		PowerMockito.whenNew(UploadTask.class).withNoArguments().thenReturn(uploadTaskMock);
		UploadTask testInstance = new UploadTask();
		assertEquals(uploadTaskMock, testInstance);
	}

	@Test
	public void testUploadTaskUploadTaskStatusString() throws Exception {
		PowerMockito.whenNew(UploadTask.class).withArguments(Mockito.any(UploadTaskStatus.class), Mockito.anyString())
				.thenReturn(uploadTaskMock);
		UploadTask testInstance = new UploadTask(UploadTaskStatus.SUCCESS, "test");
		assertEquals(uploadTaskMock, testInstance);
	}

	@Test
	public void testGetTaskId() {
		when(uploadTaskMock.getTaskid()).thenReturn(100L);
		assertEquals(100L, uploadTaskMock.getTaskid());
	}

	@Test
	public void testSetTaskId() {

		doAnswer(invocationOnMock -> {
			Long injectedLong = (Long) invocationOnMock.getArguments()[0];
			UploadTaskTest.this.uploadTaskMock.setTaskid(injectedLong);
			return null;
		}).when(this.uploadTaskMock).setTaskid(Mockito.anyLong());
		when(this.uploadTaskMock.getTaskid())
				.thenAnswer(invocationOnMock -> UploadTaskTest.this.uploadTaskMock.getTaskid());
	}

	@Test
	public void testGetTaskStatus() {
		when(uploadTaskMock.getTaskStatus()).thenReturn(UploadTaskStatus.SUCCESS);
		assertEquals(UploadTaskStatus.SUCCESS, uploadTaskMock.getTaskStatus());
	}

	@Test
	public void testSetTaskStatus() {

		doAnswer(invocationOnMock -> {
			UploadTaskStatus injected = (UploadTaskStatus) invocationOnMock.getArguments()[0];
			UploadTaskTest.this.uploadTaskMock.setTaskStatus(injected);
			return null;
		}).when(this.uploadTaskMock).setTaskStatus(Mockito.any(UploadTaskStatus.class));
		when(this.uploadTaskMock.getTaskStatus())
				.thenAnswer(invocationOnMock -> UploadTaskTest.this.uploadTaskMock.getTaskStatus());

	}

	@Test
	public void testGetFileName() {
		when(uploadTaskMock.getFileName()).thenReturn("Testfile");
		assertEquals("Testfile", uploadTaskMock.getFileName());
	}

	@Test
	public void testSetFileName() {

		doAnswer(invocationOnMock -> {
			String injectedString = (String) invocationOnMock.getArguments()[0];
			UploadTaskTest.this.uploadTaskMock.setFileName(injectedString);
			return null;
		}).when(this.uploadTaskMock).setFileName(Mockito.anyString());
		when(this.uploadTaskMock.getFileName())
				.thenAnswer(invocationOnMock -> UploadTaskTest.this.uploadTaskMock.getFileName());

	}

}
