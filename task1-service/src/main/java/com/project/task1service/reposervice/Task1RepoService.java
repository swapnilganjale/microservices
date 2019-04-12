package com.project.task1service.reposervice;

import java.util.List;

 
import com.project.task1service.dto.Task1DTO;
import com.project.task1service.exception.CustomException;
import com.project.task1service.model.Task1;

public interface Task1RepoService {

	Task1DTO saveTask1(Task1 task) throws CustomException;

	List<Task1DTO> findAllTasks() throws CustomException;

}