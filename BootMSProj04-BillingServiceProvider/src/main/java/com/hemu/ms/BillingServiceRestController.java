package com.hemu.ms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing-api")
public class BillingServiceRestController {

	@GetMapping("/show-amt")
	public ResponseEntity<String> getBillAmt()
	{
		return new ResponseEntity<String>("The bill amt is"+14000,HttpStatus.OK);
	}
	
}
