package com.project_t.task.config;

import org.apache.tomcat.util.net.DispatchType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.DispatcherType;

@Configuration
public class SecurityConfiguration {
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests((authorize) -> authorize
        .dispatcherTypeMatchers(DispatcherType.FORWARD,
            DispatcherType.ERROR)
        .permitAll()
        .requestMatchers("/tasks/create", "/tasks/delete", "/tasks/*/edit", "/profile").authenticated()
        .requestMatchers("/", "/tasks", "/tasks/*", "/login", "/register", "/tasks/show", "/tasks/{id}")
        .permitAll()
        .requestMatchers("/js/*", "/css/*", "/img/*").permitAll())
        .formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/tasks"))
        .logout(logout -> logout.logoutSuccessUrl("/login"));
    return http.build();
  }
}
