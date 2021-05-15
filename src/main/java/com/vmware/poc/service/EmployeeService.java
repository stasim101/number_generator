package com.vmware.poc.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.vmware.poc.exception.EmployeeNotFoundException;
import com.vmware.poc.model.Employee;
import com.vmware.poc.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveAnEmployee(Employee employee) {
		logger.info("Saving an employee");
		return employeeRepository.save(employee);
	}

	public Employee getAnEmployee(long id) {
		logger.info("Searching an employee");
		return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
	}

	public Employee updateAnEmployee(long id, Employee employee) {
		Employee storedEmployee = getAnEmployee(id);

		if (storedEmployee == null)
			return null;

		if (StringUtils.hasLength(employee.getEmployeeName()))
			storedEmployee.setEmployeeName(employee.getEmployeeName());

		if (employee.getEmployeeAge() > 0)
			storedEmployee.setEmployeeAge(employee.getEmployeeAge());

		logger.info("Updating an employee");
		return saveAnEmployee(storedEmployee);
	}

	public boolean deleteAnEmployee(long id) {

		if (getAnEmployee(id) == null)
			return false;

		logger.info("Deleting employee");
		employeeRepository.deleteById(id);
		return true;

	}

	public List<Employee> getEmployeeList() {
		logger.info("Fetching all employees");
		return (List<Employee>) employeeRepository.findAll();
	}

	public List<Employee> saveEmployeeList(List<Employee> employees) {
		logger.info("Saving employees batch");
		return (List<Employee>) employeeRepository.saveAll(employees);
	}

}
