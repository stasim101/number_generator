package com.vmware.poc.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vmware.poc.model.Employee;
import com.vmware.poc.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@PostMapping
	public long uploadEmployeeDataFile() {
		return 0L;
	}

	@PutMapping(path= "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> createNewEmployee(@RequestBody Employee employee) {

		//add resource
    	employee = employeeRepository.save(employee);
        
        //Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{id}")
                                    .buildAndExpand(employee.getId())
                                    .toUri();
        
        //Send location in response
        return ResponseEntity.created(location).build();
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
