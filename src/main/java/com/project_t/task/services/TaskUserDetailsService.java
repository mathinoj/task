package com.project_t.task.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project_t.task.models.TaskUserDetails;
import com.project_t.task.models.User;
import com.project_t.task.repositories.UserRepository;

@Service
public class TaskUserDetailsService implements UserDetailsService {
  public final UserRepository userDao;

  public TaskUserDetailsService(UserRepository userDao) {
    this.userDao = userDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userDao.findUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User Deets not found for username " + username);
    } else {
      return new TaskUserDetails(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());
    }
  }

}
