package com.project.task1service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({ "classpath:exception.properties" })
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class Task1ServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Task1ServiceApplication.class, args);
	}

}
