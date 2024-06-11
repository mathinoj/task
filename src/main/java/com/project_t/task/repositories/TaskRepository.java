package com.project_t.task.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_t.task.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
  // List<Task> findByTitleOrDescription(String title, String description);
  // List<Task> findByTitleStartingWith(String title);

  List<Task> findByTitleIsContainingOrDescriptionIsContaining(String title, String description);
  // https://stackoverflow.com/questions/52497673/how-to-use-multiple-like-keyword-in-sping-jpa-on-same-column
}
