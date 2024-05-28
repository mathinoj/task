package com.project_t.task.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project_t.task.models.User;
import com.project_t.task.repositories.UserRepository;

public class UserController {
  private final UserRepository userDao;

  public UserController(UserRepository userDao) {
    this.userDao = userDao;
  }

  @GetMapping("/profile")
  public String userProfile(Model model) {
    User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    model.addAttribute("user", loggedInUser);
    return "profile";
  }

}
