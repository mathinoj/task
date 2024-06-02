package com.project_t.task.utils;

import org.springframework.security.core.context.SecurityContextHolder;

public class Input {
  public static Object isSomeoneLoggedIn(){
    Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return user;
  }
}
