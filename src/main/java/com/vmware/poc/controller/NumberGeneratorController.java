package com.vmware.poc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.vmware.poc.model.Employee;

@Controller
public class NumberGeneratorController {

	@PostMapping
	public long uploadEmployeeDataFile() {
		return 0L;
	}

	@PutMapping
	public Employee createNewEmployee() {
		return new Employee();
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
