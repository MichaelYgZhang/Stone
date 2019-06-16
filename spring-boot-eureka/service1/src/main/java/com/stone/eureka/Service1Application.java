package com.stone.eureka;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/service1Api")
@EnableDiscoveryClient
public class Service1Application {

	@Autowired
	private RestTemplate restTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}
	
	@GetMapping(value = "service1")
	public String service1() {
		return restTemplate.getForEntity("http://service2", String.class).getBody();
	}


	@Autowired
	private DiscoveryClient discoveryClient;

	@Autowired
	private EurekaClient eurekaClient;

	private int requestCount = 1;

	@GetMapping("/getAllService2")
	public String getAllService2() {
		String result = "no instance available";
		List<ServiceInstance> instances = discoveryClient.getInstances("service2");

		if (instances != null && instances.size() > 0) {
			// 这里可以自定义路由算法, 这里采用轮训
			int size = instances.size();
			int index = requestCount%size;
			requestCount++;

			ServiceInstance instance = instances.get(index);
			// Invoke server based on host and port.
			// Example using RestTemplate.
			URI productUri = URI.create(String
					.format("http://%s:%s",
							instance.getHost(), instance.getPort()));

			//TODO ⚠️注意使用discoveryClient的远程调用的话实际上是通过ip地址来调用的，所以@LoadBalanced 要注释掉
			result = restTemplate.getForObject(productUri, String.class);
		}

		return result;
	}

	@Bean
	@LoadBalanced    //使用RestTemplate时打开
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}



	//以下练习zuul
	@Value("${server.port}")
	private int port;

	@GetMapping("/testZuul")
	public String getService() {
		String url = "http://service2/service2Api/testZuul";
		return restTemplate.getForObject(url, String.class) + ",service1 on port: (" + port + ").";
	}

}
