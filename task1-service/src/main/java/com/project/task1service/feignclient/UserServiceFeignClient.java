package com.project.task1service.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;

@Configuration
@FeignClient(name = "user-service",configuration=UserServiceFeignClientInterceptor.class)
public interface UserServiceFeignClient {

    @GetMapping("/users/getuser")
	String test();
}
 