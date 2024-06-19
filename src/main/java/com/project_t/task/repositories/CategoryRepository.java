package com.project_t.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_t.task.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Category findCategoryByName(String name);

  Category findCategoryByNameIsContaining(String categoryName);

  Category findByName(String name);
  // Category findCategoryName(String name);
  Category findById(long id);
}
