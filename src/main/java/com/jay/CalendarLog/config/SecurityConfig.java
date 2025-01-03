package com.jay.CalendarLog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Disable CSRF
                .authorizeHttpRequests(authz -> authz
                                .anyRequest().permitAll()  // Allow all requests without authentication
                )
                .httpBasic(basic -> basic.disable())  // Disable HTTP Basic authentication
                .formLogin(login -> login.disable())  // Disable form-based login
                .sessionManagement(management -> management.disable());  // Disable session management (optional if using JWT)

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Only use BCrypt for password encoding
    }
}
