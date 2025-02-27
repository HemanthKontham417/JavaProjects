package com.hemu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("BillingServiceProvider-Proj04")
public interface IFeignClientType {
	
	@GetMapping("/BillingServiceProvider/billing-api/show-amt")
	public ResponseEntity<String> getBillingClient();

}
