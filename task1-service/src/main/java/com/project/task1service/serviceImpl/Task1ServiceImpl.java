package com.project.task1service.serviceImpl;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.task1service.dto.Task1DTO;
import com.project.task1service.exception.CustomException;
import com.project.task1service.exception.CustomExceptionCreatorService;
import com.project.task1service.model.Task1;
import com.project.task1service.orikamapper.OrikaMapper;
import com.project.task1service.reposervice.Task1RepoService;
import com.project.task1service.service.Task1Service;
 
@Service
public class Task1ServiceImpl implements Task1Service {

	@Autowired
	private Task1RepoService task1RepoService;

	@Autowired
	private OrikaMapper orikamapper;

	@Autowired
	private CustomExceptionCreatorService customExceptionCreatorService;
    
	 
	@Override
	public Task1DTO saveTask1(Task1DTO task) throws CustomException {
 		try {
 	        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

 			task.setCreatedAt(timestamp.toString());
 			task.setStatus("Queued");
 			task.setPercentage("0");

			return task1RepoService.saveTask1(orikamapper.map(task, Task1.class));
		} catch (Exception e) {
			throw customExceptionCreatorService.getCustomException("0001");
		}
	}

	@Override
	public List<Task1DTO> getAllTasks() throws CustomException {
		try {
			return task1RepoService.findAllTasks();
		} catch (Exception e) {
			throw customExceptionCreatorService.getCustomException("0001");
		}
	}
 
}