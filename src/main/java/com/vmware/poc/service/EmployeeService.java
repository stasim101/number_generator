package com.vmware.poc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vmware.poc.model.Employee;
import com.vmware.poc.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private static final String INVALID_ID = "Invalid employee id !";

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveAnEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public String getAnEmployee(long id) {
		Employee employeeInstance = getEmployeeFromRepository(id);
		return (employeeInstance != null) ? convertToJSONString(employeeInstance) : INVALID_ID;
	}

	public String updateAnEmployee(long id, Employee newEmployee) {
		Employee retrieved = getEmployeeFromRepository(id);

		if (retrieved == null)
			return INVALID_ID;

		if (newEmployee.getEmployeeName() != null)
			retrieved.setEmployeeName(newEmployee.getEmployeeName());
		if (newEmployee.getEmployeeAge() > 0)
			retrieved.setEmployeeAge(newEmployee.getEmployeeAge());
		return convertToJSONString(employeeRepository.save(retrieved));
	}

	public String deleteAnEmployee(long id) {
		Employee toBeDeleted = getEmployeeFromRepository(id);
		
		if (toBeDeleted == null)
			return INVALID_ID;

		employeeRepository.delete(toBeDeleted);
		return "Employee deleted successfully.";
	}

	// Helper methods downwards
	private String convertToJSONString(Employee employeeObject) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(employeeObject);
		} catch (JsonProcessingException e) {
			return "Couldn't print employee data.Try Again !";
		}
	}

	private Employee getEmployeeFromRepository(long id) {
		Optional<Employee> optionalEmployee = employeeRepository.findById(id);
		if (optionalEmployee.isPresent())
			return optionalEmployee.get();
		return null;
	}
	
	
}
