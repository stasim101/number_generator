package com.vmware.poc.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vmware.poc.model.Employee;
import com.vmware.poc.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
	private static final String NOT_FOUND = "Employee not found";

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveAnEmployee(Employee employee) {
		logger.debug("Saving an employee");
		return employeeRepository.save(employee);
	}

	public Employee getAnEmployee(long id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if (employeeOptional.isPresent()) {
			logger.debug("Fetching an employee");
			return employeeOptional.get();
		}
		logger.error(NOT_FOUND);
		return null;
	}

	public Employee updateAnEmployee(long id, Employee employee) {
		Employee storedEmployee = getAnEmployee(id);

		if (storedEmployee == null) {
			logger.error(NOT_FOUND);
			return null;
		}

		logger.debug("Updating an employee");
		return saveAnEmployee(storedEmployee);
	}

	public boolean deleteAnEmployee(long id) {

		if (getAnEmployee(id) == null)
			return false;

		logger.debug("Deleting employee");
		employeeRepository.deleteById(id);
		return true;

	}

	public List<Employee> getEmployeeList() {
		logger.debug("Fetching all employees");
		return (List<Employee>) employeeRepository.findAll();
	}
}
