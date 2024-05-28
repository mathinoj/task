package com.project_t.task.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

  @PostMapping("/profile")
  public String changeProfile(@RequestParam(name = "email") String email) {
    User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = userDao.findUserById(loggedInUser.getId());
    user.setEmail(email);
    userDao.save(user);
    loggedInUser.setEmail(email);
    return "redirect:/profile";
  }
}
