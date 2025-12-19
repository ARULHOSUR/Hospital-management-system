package com.hms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.disable())
      .cors(Customizer.withDefaults())
      .authorizeHttpRequests(auth -> auth
          .requestMatchers("/h2-console/**").permitAll()
          .requestMatchers("/api/**").permitAll()  // open APIs for now
          .anyRequest().permitAll()
      )
      .headers(h -> h.frameOptions(f -> f.disable())); // allow H2 console in frame

    return http.build();
  }
}
