package com.project_t.task.controllers;

import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.hibernate.grammars.hql.HqlParser.IsEmptyPredicateContext;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project_t.task.exception.ApiExceptionHandler;
import com.project_t.task.exception.ApiRequestException;
import com.project_t.task.models.Category;
import com.project_t.task.models.Task;
import com.project_t.task.models.User;
import com.project_t.task.repositories.CategoryRepository;
import com.project_t.task.repositories.TaskRepository;
import com.project_t.task.repositories.UserRepository;

import com.project_t.task.utils.Input;

import jakarta.servlet.http.HttpServletRequest;

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
  public String getAllTasks(Model model, @PageableDefault(value = 2) Pageable pageable) {
    model.addAttribute("listAllTasks", taskDao.findAll(pageable));
    return "/tasks/index";
  }

  @GetMapping("/tasks/myTasks")
  public String getUsersOwnTasks(HttpServletRequest request, Model model) {
    long userId = Input.userIsLoggedIn().id;
    String getWhatUserPicked = request.getParameter("search");

    if (getWhatUserPicked == null) {
      model.addAttribute("tf", taskDao.findById(userId));
      model.addAttribute("userId", userId);
      model.addAttribute("userSpecificTasks", taskDao.findAll());
    } else if (getWhatUserPicked.equals("true") || getWhatUserPicked.equals("false")) {
      List<Task> findComplete = taskDao.findByIsComplete(getWhatUserPicked);
      model.addAttribute("complete", findComplete);
      model.addAttribute("userId", userId);
    }
    return "/tasks/myTasks";
  }

  @GetMapping("/tasks/search")
  public String searchTaskByTitle(
      @RequestParam(name = "search") String title,
      @RequestParam(name = "search") String description,
      @RequestParam(name = "search") String categoryName,
      HttpServletRequest request,
      // https://stackoverflow.com/questions/17291849/get-textfield-string-input-from-html-file-to-process-use-in-java-class
      Model model) {
    String getWhatUserTyped = request.getParameter("search");
    if (getWhatUserTyped == null || getWhatUserTyped.equals(null) || getWhatUserTyped.isBlank()
        || getWhatUserTyped.isEmpty()) {
      model.addAttribute("searchBlank", "Search cannot be Blank!");
      return "error";
    }

    List<Task> searchTasks = taskDao.findByTitleIsContainingOrDescriptionIsContaining(title, description);
    Category categories = categoryDao.findCategoryByNameIsContaining(categoryName);

    if (searchTasks.isEmpty()) {
      model.addAttribute("nothingFound", "Your search turned up Nathan!");
      return "error";
    }
    if (categories == null) {
      model.addAttribute("results", searchTasks);
    } else {
      Long getCategoryId = categories.getId();
      model.addAttribute("results2", taskDao.findByCategoriesId(getCategoryId));
      model.addAttribute("categoryName", categories.getName());
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
    model.addAttribute("newCategory", new Category());
    return "/tasks/create";
  }

  @PostMapping("/tasks/create")
  public String postTask(@ModelAttribute Task tasker,
      @RequestParam(name = "cater", required = false) List<String> categories, @ModelAttribute Category newCategory,
      @RequestParam(name = "name", required = false) String name, Model model) {
    long userId = Input.userIsLoggedIn().id;
    tasker.setUser(userDao.findUserById(userId));

    LocalDate date = LocalDate.now();
    tasker.setPublishDate(date.toString());
    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
    // String publishDate = date.format(formatter);
    // tasker.setPublishDate(publishDate);

    if (categories == null || categories.equals(null)) {
      List<Category> categoriesFindAll = categoryDao.findAll();
      for (Category categoryLoop : categoriesFindAll) {
        if (categoryLoop.getName() == newCategory.getName() || categoryLoop.getName().equals(newCategory.getName())) {
          model.addAttribute("errorCategoryExists", "Category already Exists!");
          return "error";
        }
      }

      if (newCategory.getName().isEmpty()) {
        model.addAttribute("errorCategory", "Category cannot be Blank!");
        return "error";
      } else {
        newCategory.setName(name);
        categoryDao.save(newCategory);
        return "redirect:/tasks/create";
      }
    } else {
      List<Category> categoryList = new ArrayList<>();
      for (String category : categories) {
        Category categoryFromDB = categoryDao.findCategoryByName(category);
        categoryList.add(categoryFromDB);
      }

      String title = tasker.getTitle();
      String description = tasker.getDescription();
      String dateTime = tasker.getTaskDueDate();
      if (title.isBlank() || description.isBlank() || dateTime.isBlank()) {
        // throw new ApiRequestException("Input field is blank!");
        model.addAttribute("error", "Input field(s) cannot be Blank!");
        return "error";
      }

      // String getOldDateFormat = tasker.getTaskDueDate();
      // System.out.println("old date format: " + getOldDateFormat);
      // LocalDate parseOldTOLocalDate = LocalDate.parse(getOldDateFormat);
      // DateTimeFormatter newFormatDate = DateTimeFormatter.ofPattern("dd LLLL
      // yyyy");
      // String setNewDateFormat = parseOldTOLocalDate.format(newFormatDate);
      // tasker.setTaskDueDate(setNewDateFormat);

      String categoryToString = String.join(", ", categories);
      if ((newCategory != null || categoryToString == null)) {
        tasker.setIsComplete("false");
        tasker.setCategories(categoryList);
        taskDao.save(tasker);
      }

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
      @RequestParam long userId, Model model) {
    task.setUser(userDao.findUserById(userId));
    List<Category> categoryList = new ArrayList<>();
    for (String category : categories) {
      Category categoryFromDB = categoryDao.findCategoryByName(category);
      categoryList.add(categoryFromDB);
    }

    // System.out.println(task.getId());
    task.setCategories(categoryList);
    if (task.getTitle().isEmpty() || task.getDescription().isEmpty()) {
      model.addAttribute("taskId", task.getId());
      model.addAttribute("error", "Input field(s) cannot be Blank!");
      return "/error";
    }

    taskDao.save(task);
    return "redirect:/tasks/myTasks";
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
