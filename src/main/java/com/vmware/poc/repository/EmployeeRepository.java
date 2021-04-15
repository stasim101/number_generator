package com.vmware.poc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.vmware.poc.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
