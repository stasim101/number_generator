package com.vmware.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.vmware.poc.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employee")
	public long uploadEmployeeDataFile(@RequestParam("file") MultipartFile file, @RequestParam String action) {
		return 0L;
	}

	@PutMapping("/create")
	public ResponseEntity<Object> createNewEmployee(@RequestBody Employee employee) {
	//	return new ResponseEntity<>(employeeService.saveAnEmployee(employee), HttpStatus.CREATED);
		return null;
	//ResponseEntity<Response> result = restTemplate.exchange(uri, HttpMethod.GET, entity, Response.class);     
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Object> retreiveAnEmployee(@PathVariable("id") long id) {
		//return ResponseEntity.ok(employeeService.getAnEmployee(id));
		return null;
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<Object> updateEmployeeData(@PathVariable("id") long id, @RequestBody Employee employee) {
		//return ResponseEntity.ok(employeeService.updateAnEmployee(id, employee));
		return null;
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteAnEmployee(@PathVariable("id") long id) {
	//	return ResponseEntity.ok(employeeService.deleteAnEmployee(id));
	return null;
	}
}
