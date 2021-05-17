package com.vmware.poc.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.vmware.poc.enums.UploadTaskStatus;
import com.vmware.poc.model.Employee;

@Service
public class FileUploadService {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UploadTaskService uploadTaskService;

	@Transactional
	public void uploadFile(MultipartFile file, long taskId) {

		List<Employee> employees = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			int lineCount = 0;

			while (bufferedReader.ready()) {

				String temp = bufferedReader.readLine();

				String age = temp.replaceAll("[^0-9]+", "");
				String employeeName = temp.replace(age, "").trim();

				employees.add(new Employee(employeeName, Integer.valueOf(age)));

				if ((lineCount + 1) % 500 == 0) {

					employees = employeeService.saveEmployeeList(employees);
					employees.clear();
				}

				lineCount++;
			}

		} catch (NumberFormatException nfe) {

			// Saving last batch of employees
			if (nfe.toString().contains("For input string: \"\""))
				employeeService.saveEmployeeList(employees);

			uploadTaskService.setTaskStatusById(taskId, UploadTaskStatus.SUCCESS);

		} catch (Exception e) {
			
			logger.warn("Error while uploading file ", e.getMessage());
			uploadTaskService.setTaskStatusById(taskId, UploadTaskStatus.FAILURE);
		}
	}

	@Async
	public void uploadFileAsync(MultipartFile file, long taskId) {
		logger.info("> sendAsync");

		try {
			uploadFile(file, taskId);
		} catch (Exception e) {
			logger.warn("Exception caught sending asynchronous data.", e.getMessage());
		}

		logger.info("< sendAsync");
	}
}
