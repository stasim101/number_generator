package com.vmware.poc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService employeeService;

	@PostMapping("/employee")
	public ResponseEntity<String> uploadEmployeeDataFile(
			@RequestParam("action") String action) throws Exception{

/*		
		
		try {

			final FileInputStream inputStream = new FileInputStream(file.getName());

			//final FileChannel channel = inputStream.getChannel();

			//MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
			ByteBuffer buffer = ByteBuffer.allocate(65536);
			ReadableByteChannel in = Channels.newChannel(inputStream);

			while (in.read(buffer) != -1) {

				buffer.flip();
				logger.info(buffer.toString());
				buffer.clear();

			}

		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		*/
		return ResponseEntity.ok(action);
	}

	@PutMapping("/create")
	public ResponseEntity<Employee> createNewEmployee(@RequestBody Employee employee) {
		return (employee.getEmployeeAge() > 0 && employee.getEmployeeName() != null)
				? new ResponseEntity<>(employeeService.saveAnEmployee(employee), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> retrieveAnEmployee(@PathVariable("id") long id) {
		return ResponseEntity.ok(employeeService.getAnEmployee(id));
	}

	@PatchMapping("/update/{id}")
	public ResponseEntity<Employee> updateEmployeeData(@PathVariable("id") long id, @RequestBody Employee employee) {
		return (StringUtils.hasLength(employee.getEmployeeName()) || employee.getEmployeeAge() > 0)
				? new ResponseEntity<>(employeeService.updateAnEmployee(id, employee), HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteEmployeeData(@PathVariable("id") long id) {
		return ResponseEntity.ok(employeeService.deleteAnEmployee(id));
	}

	@GetMapping("/all")
	public ResponseEntity<Object> getAllEmployees() {
		return ResponseEntity.ok(employeeService.getEmployeeList());
	}
}
