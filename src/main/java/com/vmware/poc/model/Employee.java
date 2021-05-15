package com.vmware.poc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String employeeName;
	private int employeeAge;

	public Employee() {
	}

	public Employee(String employeeName, int employeeAge) {
		this.employeeName = employeeName;
		this.employeeAge = employeeAge;
	}

	public long getId() {
		return id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}

	@Override
	public String toString() {
		return "{"+"employeeName:"+this.employeeName+", employeeAge"+this.employeeAge+"}";
	}
}
