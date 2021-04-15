package com.vmware.poc.repository;

import org.springframework.data.repository.CrudRepository;

import com.vmware.poc.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
