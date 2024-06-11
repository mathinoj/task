package com.project_t.task.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_t.task.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
  // List<Task> findByTitleOrDescription(String title, String description);
  // List<Task> findByTitleIgnoreCaseStartingWith(String title);

  List<Task> findByTitleStartingWith(String title);

  // List<Task> findByTitleEndingWith(String title);

  // List<Task> findByTitleStartingWithOrTitleEndingWith(String title, String
  // key);

  // List<Task> findByDescriptionStartingWithOrDescriptionEndingWith(String
  // description, String key);
  List<Task> findByTitleIsContaining(String title);

  List<Task> findByTitleIsContainingOrDescriptionIsContaining(String title, String description);

  List<Task> findByDescriptionIsContaining(String description);
}
