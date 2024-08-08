package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF protection
            .authorizeRequests()
            .antMatchers("/books/public/view").permitAll()  // Allow access to /books/public/view without authentication
            .antMatchers("/books/**").authenticated()  // Secure all other /books endpoints
            .antMatchers("/h2-console/**").permitAll()  // Allow access to H2 console without authentication
            .anyRequest().permitAll()  // Allow all other endpoints
            .and()
            .headers().frameOptions().disable()  // Disable X-Frame-Options for H2 console
            .and()
            .httpBasic();  // Enable Basic Authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
            .password("{noop}password")  // {noop} is a password encoder that does nothing, just for testing
            .roles("USER")
            .build());
        return manager;
    }
}
