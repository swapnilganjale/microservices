package com.project.task1service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.task1service.model.Task1;

@Repository("task1Repository")
public interface Task1Repository extends JpaRepository<Task1, Integer> {
}