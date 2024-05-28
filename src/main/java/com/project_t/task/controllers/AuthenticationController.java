package com.project_t.task.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {
  @GetMapping("/login")
  public String showLoginForm() {
    return "/login";
  }

}
