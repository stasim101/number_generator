package com.vmware.poc.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(NOT_FOUND)
@ControllerAdvice
public class TaskNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2332950075703196835L;

	private static final Logger logger = LoggerFactory.getLogger(TaskNotFoundException.class);

	public TaskNotFoundException() {
		super();
		logger.info("Task not found");
	}
}
