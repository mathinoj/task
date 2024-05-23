package com.project_t.task.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_t.task.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
  List<Task> findByTitle(String title);
}
