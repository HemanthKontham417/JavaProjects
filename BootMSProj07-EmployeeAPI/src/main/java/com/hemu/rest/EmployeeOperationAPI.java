package com.hemu.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Employee-api")
public class EmployeeOperationAPI {

	@GetMapping("/response")
	public ResponseEntity<String> getEmployeeREsponse()
	{
		return new ResponseEntity<String>("Response from Employee API Service Operation",HttpStatus.OK);
	}
	
}
