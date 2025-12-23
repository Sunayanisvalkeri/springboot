package com.example.ClassRosterWebService.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF to avoid form redirect issues
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/students",
                                "/addStudent",
                                "/deleteStudent",
                                "/courses",
                                "/addCourse",
                                "/deleteCourse",
                                "/teachers",
                                "/css/**",
                                "/js/**"
                        ).permitAll()
                        .anyRequest().permitAll()
                )
                .formLogin(login -> login.disable())
                .logout(logout -> logout.disable());

        return http.build();
    }
}
