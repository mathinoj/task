package com.project_t.task.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project_t.task.models.Task;
import com.project_t.task.models.Category;

public interface TaskRepository extends JpaRepository<Task, Long> {
  // List<Task> findByTitleOrDescription(String title, String description);
  // List<Task> findByTitleStartingWith(String title);

  List<Task> findByTitleIsContainingOrDescriptionIsContaining(String title, String description);
  // https://stackoverflow.com/questions/52497673/how-to-use-multiple-like-keyword-in-sping-jpa-on-same-column
  // https://docs.spring.io/spring-data/jpa/reference/repositories/query-keywords-reference.html

  List<Task> findByCategories(List<Category> categories);

  // List<Task> findByCategoryName(String name);
  List<Task> findByCategoriesId(long id);

  List<Task> findCategoriesById(long id);

  List<Task> findByIsComplete(String isComplete);

  // Object findAll(Pageable pageable);

  Page<Task> findAll(Pageable pageable);


}
