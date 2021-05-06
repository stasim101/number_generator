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
	private static final String EMPLOYEE_NOT_FOUND = "Employee not found with id:";
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveAnEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee getAnEmployee(long id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(id);
		if (employeeOptional.isPresent())
			return employeeOptional.get();
		logger.error(EMPLOYEE_NOT_FOUND + id);
		return null;
	}

	public Employee updateAnEmployee(long id, Employee employee) {

		Employee storedEmployee = getAnEmployee(id);

		if (storedEmployee == null) {
			logger.error(EMPLOYEE_NOT_FOUND + id);
			return null;
		}

		if (!employee.getEmployeeName().isEmpty())
			storedEmployee.setEmployeeName(employee.getEmployeeName());

		if (employee.getEmployeeAge() > 0)
			storedEmployee.setEmployeeAge(employee.getEmployeeAge());

		return saveAnEmployee(storedEmployee);
	}

	public void deleteAnEmployee(long id) {
		employeeRepository.deleteById(id);
	}

	public List<Employee> getEmployeeList() {
		return (List<Employee>) employeeRepository.findAll();
	}
}
