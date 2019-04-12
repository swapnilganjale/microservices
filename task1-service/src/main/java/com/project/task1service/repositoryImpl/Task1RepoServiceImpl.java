package com.project.task1service.repositoryImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.task1service.dto.Task1DTO;
import com.project.task1service.exception.CustomException;
import com.project.task1service.model.Task1;
import com.project.task1service.orikamapper.OrikaMapper;
import com.project.task1service.reposervice.Task1RepoService;
import com.project.task1service.repository.Task1Repository;
 

@Service
public class Task1RepoServiceImpl implements Task1RepoService {

	@Autowired
	private Task1Repository task1Repository;

	@Autowired
	private OrikaMapper orikaMapper;
 
	@Override
	public List<Task1DTO> findAllTasks() throws CustomException {
 		return orikaMapper.mapAsList(task1Repository.findAll(), Task1DTO.class);

	}

	@Override
	public Task1DTO saveTask1(Task1 task) throws CustomException {
		 return orikaMapper.map(task1Repository.save(task), Task1DTO.class);
	}
 

}