package com.vmware.poc.excpetion;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
@ControllerAdvice
public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5490406453287190535L;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeNotFoundException.class);

	public EmployeeNotFoundException() {
		super();
		logger.info("Employee not found");
	}
}
