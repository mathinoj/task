package com.project_t.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_t.task.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{


}
