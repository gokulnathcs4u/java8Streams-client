package com.java8streams.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Java8streamsClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(Java8streamsClientApplication.class, args);
	}

}
