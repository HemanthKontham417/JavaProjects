package com.hemu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BootMsProj07EmployeeApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootMsProj07EmployeeApiApplication.class, args);
	}

}
