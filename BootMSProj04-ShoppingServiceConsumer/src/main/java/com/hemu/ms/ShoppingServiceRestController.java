package com.hemu.ms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hemu.client.IFeignClientType;

@RestController
public class ShoppingServiceRestController {

	@Autowired
	private IFeignClientType client;
	
	@GetMapping("/show-bill")
	public ResponseEntity<String> getBillInfo()
	{
		ResponseEntity<String> billingClient = client.getBillingClient();
		return new ResponseEntity<String>(billingClient.getBody()+" is amount for shopping in Zudio....",HttpStatus.OK);
	}
}
