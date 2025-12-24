package com.example.ClassRosterWebService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // Disable CSRF (THIS FIXES YOUR ERROR)
                .csrf(csrf -> csrf.disable())

                // Allow all requests
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // Disable login page redirect
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
