package com.hemu.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Customer-api")
public class CustomerOperationAPI {

	@GetMapping("/response")
	public ResponseEntity<String> getCustomerResponse()
	{
		return new ResponseEntity<String>("Response from Customer API service Operation",HttpStatus.OK);
	}
	
}
