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
import org.springframework.web.bind.annotation.RestController;

import com.vmware.poc.model.Employee;
import com.vmware.poc.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public long uploadEmployeeDataFile() {
		return 0L;
	}

	@PutMapping("/create")
	public ResponseEntity<Object> createNewEmployee(@RequestBody Employee employee) {
		return new ResponseEntity<>(employeeService.saveAnEmployee(employee), HttpStatus.CREATED);
	}

	@GetMapping("/find/{id}")
	public String retreiveAnEmployee(@PathVariable("id") long id) {
		return employeeService.getAnEmployee(id);
	}

	@PatchMapping("/update/{id}")
	public String updateEmployeeData(@PathVariable("id") long id, @RequestBody Employee employee) {
		return employeeService.updateAnEmployee(id, employee);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteAnEmployee(@PathVariable("id") long id) {
		return employeeService.deleteAnEmployee(id);
	}
}
