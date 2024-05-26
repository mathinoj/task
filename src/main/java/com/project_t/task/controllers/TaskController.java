package com.project_t.task.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project_t.task.models.Category;
import com.project_t.task.models.Task;
import com.project_t.task.repositories.CategoryRepository;
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
  private final CategoryRepository categoryDao;

  public TaskController(TaskRepository taskDao, UserRepository userDao, CategoryRepository categoryDao) {
    this.taskDao = taskDao;
    this.userDao = userDao;
    this.categoryDao = categoryDao;
  }

  // @GetMapping("/tasks")
  // public String getAllTasks(Model model) {
  // model.addAttribute("tasking", tasks);
  // return "/tasks/index";
  // }

  @GetMapping("/tasks")
  public String getAllTasks(Model model) {
    model.addAttribute("listAllTasks", taskDao.findAll());
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
    List<Category> categories = categoryDao.findAll();
    categories.sort(Comparator.comparing(Category::getName));
    model.addAttribute("cat", categories);
    model.addAttribute("tasker", new Task());
    return "/tasks/create";
  }

  @PostMapping("/tasks/create")
  public String postTask(@ModelAttribute Task tasker, @RequestParam(name = "cater") List<String> categories) {
    tasker.setUser(userDao.findUserById(1L));
    List<Category> categoryList = new ArrayList<>();
    for (String category : categories) {
      Category categoryFromDB = categoryDao.findCategoryByName(category);
      categoryList.add(categoryFromDB);
    }
    tasker.setCategories(categoryList);
    taskDao.save(tasker);
    return "redirect:/tasks";
  }

  @GetMapping("/tasks/{id}/edit")
  public String getEditTask(@PathVariable long id, Model model) {
    Task task;
    if (taskDao.findById(id).isPresent()) {
      task = taskDao.findById(id).get();
      List<Category> categories = categoryDao.findAll();
      categories.sort(Comparator.comparing(Category::getName));
      model.addAttribute("cat", categories);
    } else {
      task = null;
    }
    model.addAttribute("task", task);
    return "/tasks/edit";
  }

  @PostMapping("/tasks/{id}/edit")
  public String doEditTask(@ModelAttribute Task task, @RequestParam(name = "cater") List<String> categories,
      @RequestParam long userId) {
    task.setUser(userDao.findUserById(userId));
    List<Category> categoryList = new ArrayList<>();
    for (String category : categories) {
      Category categoryFromDB = categoryDao.findCategoryByName(category);
      categoryList.add(categoryFromDB);
    }
    task.setCategories(categoryList);
    taskDao.save(task);
    return "redirect:/tasks";
  }

  @GetMapping("/tasks/addCategory")
  public String addCat(){
    return "tasks/addCategory";
  }

}
