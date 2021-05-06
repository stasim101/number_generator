package com.vmware.poc.controller;

import java.util.List;

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
	public ResponseEntity createNewEmployee(@RequestBody Employee employee) {
		return (employee.getEmployeeAge() > 0 && employee.getEmployeeName() != null)
				? new ResponseEntity<Employee>(employeeService.saveAnEmployee(employee), HttpStatus.OK)
				: new ResponseEntity<String>("Employee details cannot be saved", HttpStatus.NO_CONTENT);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity retrieveAnEmployee(@PathVariable("id") long id) {
		Employee employee = employeeService.getAnEmployee(id);
		return (employee == null)
				? new ResponseEntity<String>("Employee with id:" + id + " cannot be found", HttpStatus.NOT_FOUND)
				: new ResponseEntity<Employee>(employee, HttpStatus.FOUND);
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity updateEmployeeData(@PathVariable("id") long id, @RequestBody Employee employee) {
		if (employee.getEmployeeAge() > 0 && employee.getEmployeeName() != null) {
			
			Employee employeeBody = employeeService.updateAnEmployee(id, employee);		
			return (employeeBody == null)
					? new ResponseEntity<String>("Employee with id:" + id + " cannot be found", HttpStatus.NOT_FOUND)
					: new ResponseEntity<Employee>(employeeBody, HttpStatus.OK);
		}
		return new ResponseEntity<String>("Employee details cannot be updated", HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteAnEmployee(@PathVariable("id") long id) {
		return (employeeService.deleteAnEmployee(id))
				? new ResponseEntity<>("Employee with id:" + id + " deleted successfully", HttpStatus.OK)
				: new ResponseEntity<>("Employee with id:" + id + " cannot be found", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/all")
	public ResponseEntity getEmployeeList() {
		List<Employee> employeeList = employeeService.getEmployeeList();
		return (employeeList.isEmpty())
				? new ResponseEntity<String>("Employee details are not stored in DB", HttpStatus.NO_CONTENT)
				: new ResponseEntity<List<Employee>>(employeeList, HttpStatus.OK);
	}
}
