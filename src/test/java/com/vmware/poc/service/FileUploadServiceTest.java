package com.vmware.poc.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;

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

	@Mock
	private MultipartFile file;

	@Test
	public void testUploadFile() {
		doThrow(new RuntimeException()).when(fileUploadService).uploadFile(Mockito.any(MultipartFile.class),
				Mockito.anyLong());
		fileUploadService.uploadFile(file, 100L);
	}

	@Test
	public void testUploadFileAsync() {
		doThrow(new RuntimeException()).when(fileUploadService).uploadFileAsync(Mockito.any(MultipartFile.class),
				Mockito.any(Long.class));
		fileUploadService.uploadFileAsync(file, 100L);
	}

}
