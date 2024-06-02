package com.project_t.task.utils;

import org.springframework.security.core.context.SecurityContextHolder;

import com.project_t.task.models.User;

public class Input {
  public static Object isSomeoneLoggedIn(){
    Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return user;
  }

  public static User userIsLoggedIn(){
    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return user;
  }
}
