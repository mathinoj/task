package com.project_t.task.controllers;

import java.time.LocalDate;

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
import com.project_t.task.models.User;
import com.project_t.task.repositories.CategoryRepository;
import com.project_t.task.repositories.TaskRepository;
import com.project_t.task.repositories.UserRepository;

import com.project_t.task.utils.Input;

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

  @GetMapping("/tasks/myTasks")
  public String getUsersOwnTasks(Model model) {
    long userId = Input.userIsLoggedIn().id;
    model.addAttribute("userId", userId);
    model.addAttribute("userSpecificTasks", taskDao.findAll());
    return "/tasks/myTasks";
  }

  @GetMapping("/tasks/search")
  public String searchTaskByTitle(
      @RequestParam(name = "search") String title,
      @RequestParam(name = "search") String description,
      @RequestParam(name = "search") String name,
      Model model) {
    List<Task> searchTsks = taskDao.findByTitleIsContainingOrDescriptionIsContaining(title, description);
    Category categories = categoryDao.findCategoryByNameIsContaining(name);
    if (categories == null) {
      model.addAttribute("results", searchTsks);
    } else {
      Long g = categories.getId();
      model.addAttribute("results2", taskDao.findByCategoriesId(g));
      model.addAttribute("cat", categories.getName());
      System.out.println("here: " + categories);
    }
    return "/tasks/index";

  }

  @GetMapping("/tasks/{id}")
  public String showOneTask(@PathVariable long id, Model model) {
    boolean checkIfTaskIdIsInTaskDao = taskDao.findById(id).isPresent();
    if (checkIfTaskIdIsInTaskDao == false) {
      return "redirect:/tasks";
    }

    Task task;
    task = taskDao.findById(id).get();
    if (Input.checkIfUserLoggedIn == "anonymousUser") {
      // task = new Task("Task unfounded", "");
      task = taskDao.findById(id).get();
      // return "/tasks/show";
    } else {
      long userId = Input.userIsLoggedIn().id;
      model.addAttribute("userId", userId);
      model.addAttribute(task);
      return "/tasks/show";
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
    model.addAttribute("newCat", new Category());
    return "/tasks/create";
  }

  @PostMapping("/tasks/create")
  public String postTask(@ModelAttribute Task tasker,
      @RequestParam(name = "cater", required = false) List<String> categories, @ModelAttribute Category newCat,
      @RequestParam(name = "name", required = false) String name, Model model) {
    long userId = Input.userIsLoggedIn().id;
    tasker.setUser(userDao.findUserById(userId));

    LocalDate date = LocalDate.now();
    tasker.setPublishDate(date.toString());

    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
    // String publishDate = date.format(formatter);
    // tasker.setPublishDate(publishDate);

    if (categories == null) {
      newCat.setName(name);
      categoryDao.save(newCat);
      ;
      return "redirect:/tasks/create";
    } else {
      List<Category> categoryList = new ArrayList<>();
      for (String category : categories) {
        Category categoryFromDB = categoryDao.findCategoryByName(category);
        categoryList.add(categoryFromDB);
      }

      if (tasker.getTitle() == null || tasker.getDescription() == null || tasker.getTitle().isEmpty()
          || tasker.getDescription().isEmpty()) {
        return showCreateForm(model);
      }

      // if (tasker.getTitle() == null || tasker.getDescription() == null ||
      // tasker.getTitle().isEmpty()
      // || tasker.getDescription().isEmpty() || categories == null ||
      // newCat.getName() == null) {
      // return showCreateForm(model);
      // }

      // String getOldDateFormat = tasker.getTaskDueDate();
      // System.out.println("old date format: " + getOldDateFormat);
      // LocalDate parseOldTOLocalDate = LocalDate.parse(getOldDateFormat);
      // DateTimeFormatter newFormatDate = DateTimeFormatter.ofPattern("dd LLLL
      // yyyy");
      // String setNewDateFormat = parseOldTOLocalDate.format(newFormatDate);
      // tasker.setTaskDueDate(setNewDateFormat);
      tasker.setIsComplete("false");
      tasker.setCategories(categoryList);
      taskDao.save(tasker);

      return "redirect:/tasks";
    }
  }

  @GetMapping("/tasks/{id}/edit")
  public String getEditTask(@PathVariable long id, Model model) {
    Task task;

    long loggedInUser = Input.userIsLoggedIn().id;
    User user = userDao.findUserById(loggedInUser);
    boolean isTaskPresent = taskDao.findById(id).isPresent();
    long userIdNumber = user.getId();
    Task findTaskById = taskDao.findById(id).get();
    long getTaskByUserId = findTaskById.getUser().getId();
    if (getTaskByUserId != userIdNumber) {
      return "redirect:/tasks";
    } else if (isTaskPresent) {
      // if (taskDao.findById(id).isPresent()) {
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

  @GetMapping("/tasks/{id}/delete")
  public String deleteTask(@PathVariable long id) {
    long loggedInUser = Input.userIsLoggedIn().id;
    User user = userDao.findUserById(loggedInUser);
    boolean isTaskPresent = taskDao.findById(id).isPresent();
    long userIdNumber = user.getId();
    Task findTaskById = taskDao.findById(id).get();
    long getTaskByUserId = findTaskById.getUser().getId();

    if (getTaskByUserId != userIdNumber) {
      return "redirect:/tasks";
    } else if (isTaskPresent) {
      taskDao.deleteById(id);
    } else {
      return "redirect:/tasks";
    }
    return "redirect:/tasks";
  }
}
