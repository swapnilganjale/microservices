package com.project.task1service.service;

import java.util.List;

import com.project.task1service.dto.Task1DTO;
import com.project.task1service.exception.CustomException;


public interface Task1Service {

 
	Task1DTO saveTask1(Task1DTO user) throws CustomException;
 
	List<Task1DTO> getAllTasks() throws CustomException;


 	
}