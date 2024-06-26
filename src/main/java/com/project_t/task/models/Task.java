package com.project_t.task.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(nullable = false, length = 100)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String publishDate;

  @Column(nullable = false)
  private String taskDueDate;

  @Column(nullable = false)
  private String isComplete;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "user_id")

  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "tasks_categories", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
  private List<Category> categories;

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(String publishDate) {
    this.publishDate = publishDate;
  }

  public String getTaskDueDate() {
    return taskDueDate;
  }

  public void setTaskDueDate(String taskDueDate) {
    this.taskDueDate = taskDueDate;
  }

  public String getIsComplete() {
    return isComplete;
  }

  public void setIsComplete(String isComplete) {
    this.isComplete = isComplete;
  }

  public Task() {
  }

  public Task(String title, String description, String publishDate, String taskDueDate, String isComplete) {
    this.title = title;
    this.description = description;
    this.publishDate = publishDate;
    this.taskDueDate = taskDueDate;
    this.isComplete = isComplete;
  }

  public Task(String title, String description, String publishDate, String taskDueDate, String isComplete, User user) {
    this.title = title;
    this.description = description;
    this.publishDate = publishDate;
    this.taskDueDate = taskDueDate;
    this.isComplete = isComplete;
    this.user = user;
  }

  

  // public Task() {
  // }

  // public Task(String title, String description, String publishDate, String taskDueDate) {
  //   this.title = title;
  //   this.description = description;
  //   this.publishDate = publishDate;
  //   this.taskDueDate = taskDueDate;
  // }

  // public Task(String title, String description, String publishDate, String taskDueDate, String isComplete) {
  //   this.title = title;
  //   this.description = description;
  //   this.publishDate = publishDate;
  //   this.taskDueDate = taskDueDate;
  //   this.isComplete = isComplete;
  // }

  // public Task(String title, String description, String publishDate, String taskDueDate, User user) {
  //   this.title = title;
  //   this.description = description;
  //   this.publishDate = publishDate;
  //   this.taskDueDate = taskDueDate;
  //   this.user = user;
  // }

  // public Task(String title, String description, String publishDate, String taskDueDate, String isComplete, User user) {
  //   this.title = title;
  //   this.description = description;
  //   this.publishDate = publishDate;
  //   this.taskDueDate = taskDueDate;
  //   this.isComplete = isComplete;
  //   this.user = user;
  // }



}
