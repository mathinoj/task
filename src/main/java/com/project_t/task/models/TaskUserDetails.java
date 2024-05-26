package com.project_t.task.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class TaskUserDetails extends User implements UserDetails {
  @Override
  public boolean isAccountNonExpired(){
    return true;
  }

  @Override
  public boolean isAccountNonLocked(){
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired(){
    return true;
  }

  @Override
  public boolean isEnabled(){
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities(){
    return null;
  }

  public TaskUserDetails(){}

  public TaskUserDetails(long id, String username, String email, String password){
    super(id, username, email, password);
  }

}
