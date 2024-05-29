package com.project_t.task.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project_t.task.models.User;
import com.project_t.task.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {
  private final UserRepository userDao;
  private final PasswordEncoder passwordEncoder;

  public UserController(UserRepository userDao, PasswordEncoder passwordEncoder) {
    this.userDao = userDao;
    this.passwordEncoder = passwordEncoder;
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

  @GetMapping("/register")
  public String showRegForm(Model model) {
    model.addAttribute("user", new User());
    return "/register";
  }

  @PostMapping("/register")
  public String regUser(@ModelAttribute User user) {
    user.setPassword(passwordEncoder.encode((user.getPassword())));
    userDao.save(user);
    return "redirect:/login";
  }

}
