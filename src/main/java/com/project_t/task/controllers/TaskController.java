package com.project_t.task.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project_t.task.models.Task;
import com.project_t.task.repositories.TaskRepository;
import com.project_t.task.repositories.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class TaskController {
  // Task task1 = new Task("task 1", "doing task 1");
  // Task task2 = new Task("task 2", "doing task 2");
  // Task task3 = new Task("task 3", "doing task 3");

  // List<Task> tasks = new ArrayList<>(List.of(task1, task2, task3));

  private final TaskRepository taskDao;
  private final UserRepository userDao;

  public TaskController(TaskRepository taskDao, UserRepository userDao) {
    this.taskDao = taskDao;
    this.userDao = userDao;
  }

  // @GetMapping("/tasks")
  // public String getAllTasks(Model model) {
  // model.addAttribute("tasking", tasks);
  // return "/tasks/index";
  // }

  @GetMapping("/tasks")
  public String getAllTasks(Model model) {
    model.addAttribute("tasking", taskDao.findAll());
    return "/tasks/index";
  }

  @GetMapping("/tasks/search")
  public String searchTaskByTitle(@RequestParam(name = "search") String title, Model model) {
    model.addAttribute("results", taskDao.findByTitle(title));
    return "/tasks/index";
  }

  @GetMapping("/tasks/{id}")
  public String showOneTask(@PathVariable long id, Model model) {
    Task task;
    if (taskDao.findById(id).isPresent()) {
      task = taskDao.findById(id).get();
    } else {
      task = new Task("Task no find!", "");
    }
    model.addAttribute(task);
    return "/tasks/show";
  }

  @GetMapping("/tasks/create")
  public String showCreateForm(Model model) {
    model.addAttribute("tasker", new Task());
    return "/tasks/create";
  }

  @PostMapping("/tasks/create")
  public String postTask(@ModelAttribute Task tasker) {
    tasker.setUser(userDao.findUserById(1L));
    taskDao.save(tasker);
    return "redirect:/tasks";
  }

  @GetMapping("/tasks/{id}/edit")
  public String getEditTask(@PathVariable long id, Model model) {
    Task task;
    if (taskDao.findById(id).isPresent()) {
      task = taskDao.findById(id).get();
    } else {
      task = null;
    }
    model.addAttribute("task", task);
    return "/tasks/edit";
  }

  @PostMapping("/tasks/{id}/edit")
  public String doEditTask(@ModelAttribute Task task, @RequestParam long userId) {
    task.setUser(userDao.findUserById(userId));
    taskDao.save(task);
    return "redirect:/tasks";
  }

}
