package com.project_t.task.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.project_t.task.models.Task;
import com.project_t.task.repositories.UserRepository;

public class TaskUserDetailsService implements UserDetailsService {
  public final UserRepository userDao;
  public TaskUserDetailsService(UserRepository userDao){
    this.userDao = userDao;
  }

  
}
