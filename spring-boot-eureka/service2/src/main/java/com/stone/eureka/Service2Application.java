package com.stone.eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@RequestMapping("/service2Api")
public class Service2Application {

	@Value("${server.port}")
	private int port;
	
	public static void main(String[] args) {
		SpringApplication.run(Service2Application.class, args);
	}
	
	@GetMapping
	public String service2Get() {
		return "Service2 running on (" + port + ")";
	}

	@GetMapping(value = "/testZuul")
	public String testZuul() {
		return "Service2 testZuul running on (" + port + ")";
	}
}
