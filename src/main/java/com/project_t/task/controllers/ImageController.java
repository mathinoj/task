package com.project_t.task.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project_t.task.models.Image;
import com.project_t.task.repositories.ImageRepository;

// Import the required packages

import com.cloudinary.*;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;

import java.util.Map;

@Controller
public class ImageController {

  private final ImageRepository imageDao;

  public ImageController(ImageRepository imageDao) {
    this.imageDao = imageDao;
  }

  @Value("${file-upload-path}")
  private String uploadPath;

  @GetMapping("/pics")
  public String getProfilePicPage(Model model) {
    System.out.println("In pics Get Mapping!");
    model.addAttribute("file", new Image());
    return "/pics";
  }

  @PostMapping("/pics")
  public String showUploadPicForm(@RequestParam(name = "file") MultipartFile uploadedFile, Model model)
      throws IOException {

    Dotenv dotenv = Dotenv.load();
    Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
    cloudinary.config.secure = true;
    System.out.println("::::::::::");

    String filename = uploadedFile.getOriginalFilename();
    System.out.println("fffff: " + filename);

    // String filepath = Paths.get(dotenv.get("CLOUDINARY_URL"),
    // filename).toString();
    // String x = "/Users/macos/Desktop/FullStack/Codeup/img/" + filename;
    String filePath = Paths.get(uploadPath, filename).toString();

    File destinationFile = new File(filePath);

    try {
      // Upload the image
      // Map params1 = ObjectUtils.asMap(
      // "use_filename", true,
      // "unique_filename", false,
      // "overwrite", true);
      Map params1 = cloudinary.uploader().upload(destinationFile, ObjectUtils.emptyMap());
      model.addAttribute("message", "File successfully uploaded!");
    } catch (IOException e) {
      System.out.println("hey you 4444");
      e.printStackTrace();
      model.addAttribute("message", "Oops! Something went wrong! " + e);
    }

    return "/pics";
  }

}
