package com.vmware.poc.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vmware.poc.model.Employee;

@Service
public class FileUploadService {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadService.class);

	@Autowired
	private EmployeeService employeeService;

	public List<Employee> uploadFile(MultipartFile file) {

		List<Employee> employees = new ArrayList<>();

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

			int lineCount = 0;

			while (bufferedReader.ready()) {

				String temp = bufferedReader.readLine();

				String age = temp.replaceAll("[^0-9]+", "");
				String employeeName = temp.replace(age, "").trim();

				employees.add(new Employee(employeeName, Integer.valueOf(age)));

				if ((lineCount + 1) % 500 == 0) {

					employeeService.saveEmployeeList(employees);
					employees.clear();
				}

				lineCount++;
			}

			logger.info(employees.toString());

		} catch (Exception e) {
			logger.error("Error : " + e.getMessage());
		} finally {
			logger.info("End of file");
			employeeService.saveEmployeeList(employees);
		}

		return employeeService.getEmployeeList();
	}

}
