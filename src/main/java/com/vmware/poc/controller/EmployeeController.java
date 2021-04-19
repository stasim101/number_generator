package com.vmware.poc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vmware.poc.model.Employee;

@RestController
public class EmployeeController {



	@PostMapping
	public long uploadEmployeeDataFile() {
		return 0L;
	}

	@PutMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> createNewEmployee(@RequestBody Employee employee) {
		
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}

	@GetMapping
	public Employee retreiveAnEmployee(long id) {
		return new Employee();
	}

	@PatchMapping
	public Employee updateEmployeeData(Employee emmployee) {
		// return updated employee object
		return new Employee();
	}

	@DeleteMapping
	public void deleteAnEmployee(long id) {
		// return deleted employee object

	}
}
