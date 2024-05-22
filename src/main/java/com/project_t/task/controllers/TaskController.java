package com.project_t.task.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.project_t.task.models.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TaskController {
  Task task1 = new Task("task 1", "doing task 1");
  Task task2 = new Task("task 2", "doing task 2");
  Task task3 = new Task("task 3", "doing task 3");

  List<Task> tasks = new ArrayList<>(List.of(task1, task2, task3));

  @GetMapping("/tasks")
  public String getAllTasks(Model model) {
      model.addAttribute("tasking", tasks);
      return "/tasks/index";
  }

}
