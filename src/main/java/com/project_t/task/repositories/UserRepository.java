package com.project_t.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project_t.task.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
  User findUserById(long id);

}
