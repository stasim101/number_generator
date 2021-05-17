package com.vmware.poc.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadServiceTest {

	@InjectMocks
	private FileUploadService fileUploadService = mock(FileUploadService.class, Mockito.RETURNS_DEEP_STUBS);

	@Mock
	private EmployeeService employeeService;

	@Test(expected = NullPointerException.class)
	public void testUploadFile() {
		Whitebox.setInternalState(fileUploadService, "employeeService", employeeService);
		doNothing().when(employeeService.saveEmployeeList(Mockito.anyList()));
		verify(fileUploadService, times(1));
		verify(employeeService, times(1));
	}

	@Test
	public void testUploadFileAsync() {
		doNothing().when(fileUploadService).uploadFileAsync(Mockito.any(MultipartFile.class), Mockito.any(Long.class));
		verify(fileUploadService, times(1));

	}

}
