package com.vmware.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vmware.poc.model.Employee;
import com.vmware.poc.model.UploadTask;
import com.vmware.poc.service.EmployeeService;
import com.vmware.poc.service.FileUploadService;
import com.vmware.poc.service.UploadTaskService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UploadTaskService uploadTaskService;

	@Autowired
	private FileUploadService fileUploadService;

	@PostMapping("/employee")
	public ResponseEntity<Object> uploadEmployeeDataFile(@RequestParam("file") MultipartFile file,
			@RequestParam("action") String action) throws Exception {
		if (!action.equals("upload"))
			return new ResponseEntity<>("Invalid action", HttpStatus.BAD_REQUEST);
		UploadTask uploadTask = uploadTaskService.generateTask(file.getOriginalFilename());
		fileUploadService.uploadFileAsync(file, uploadTask.getTaskid());
		return ResponseEntity.ok(uploadTask);
	}

	@GetMapping("/task/{id}")
	public ResponseEntity<Object> trackUploadTask(@PathVariable("id") long taskId) {
		return ResponseEntity.ok(uploadTaskService.getTaskStatusById(taskId));
	}

	@PutMapping("/create")
	public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee) {
		return (employee.getEmployeeAge() > 0 && employee.getEmployeeName() != null)
				? new ResponseEntity<>(employeeService.saveAnEmployee(employee), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> retrieveAnEmployee(@PathVariable("id") long id) {
		return ResponseEntity.ok(employeeService.getAnEmployee(id));
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployeeData(@PathVariable("id") long id, @RequestBody Employee employee) {
		return (StringUtils.hasLength(employee.getEmployeeName()) || employee.getEmployeeAge() > 0)
				? new ResponseEntity<>(employeeService.updateAnEmployee(id, employee), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteEmployeeData(@PathVariable("id") long id) {
		return ResponseEntity.ok(employeeService.deleteAnEmployee(id));
	}

	@GetMapping("/all")
	public ResponseEntity<Object> getAllEmployees() {
		return ResponseEntity.ok(employeeService.getEmployeeList());
	}
}
