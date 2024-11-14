package com.ToDo.ToDo.repository;

import com.ToDo.ToDo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, String>{
        }