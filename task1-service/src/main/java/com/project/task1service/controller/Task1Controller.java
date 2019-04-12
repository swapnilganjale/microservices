package com.project.task1service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.task1service.dto.Task1DTO;
import com.project.task1service.exception.CustomException;
import com.project.task1service.feignclient.UserServiceFeignClient;
import com.project.task1service.service.Task1Service;

@CrossOrigin
@RestController
@RequestMapping({ "/task1" })
public class Task1Controller {

	@Autowired
	private Task1Service task1Service;
	
	//@Autowired
	//UserServiceFeignClient client;
 
	@GetMapping("/getAllTask1s")
	public List<Task1DTO> getAllTasks() throws CustomException {
		System.out.println("before#####################");
		// String str=client.test();
		// System.out.println(str);
		return task1Service.getAllTasks();
	}
 
  
	@PostMapping("/add")
	public Task1DTO addTask(@RequestBody Task1DTO task1DTO) throws CustomException, JsonProcessingException {
		task1Service.saveTask1(task1DTO);
		return task1DTO;
	}

 
}
