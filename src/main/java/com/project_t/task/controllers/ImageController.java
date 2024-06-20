package com.project_t.task.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ImageController {

  // @Value("${file-upload-path}")
  // private String uploadPath;
  @GetMapping("/pics")
  public String getProfilePicPage() {
    return "/pics";
  }

}
