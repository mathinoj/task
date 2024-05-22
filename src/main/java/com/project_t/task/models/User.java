package com.project_t.task.models;

import java.security.PublicKey;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public long id;

@Column(nullable = false, length = 50, unique = true)
private String username;

@Column(nullable = false, length = 75, unique = true)
private String email;

@Column(nullable = false)
private String password;

public long getId() {
  return id;
}

public void setId(long id) {
  this.id = id;
}

public String getUsername() {
  return username;
}

public void setUsername(String username) {
  this.username = username;
}

public String getEmail() {
  return email;
}

public void setEmail(String email) {
  this.email = email;
}

public String getPassword() {
  return password;
}

public void setPassword(String password) {
  this.password = password;
}

public User() {
}

public User(String username, String email, String password) {
  this.username = username;
  this.email = email;
  this.password = password;
}

public User(long id, String username, String email, String password) {
  this.id = id;
  this.username = username;
  this.email = email;
  this.password = password;
}

@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
private List<Task> tasks;

public List<Task> getTasks(){
  return tasks;
}

public void setTasks(List<Task> tasks){
  this.tasks = tasks;
}

}
