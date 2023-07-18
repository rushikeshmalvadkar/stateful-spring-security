package com.stateful.spring.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
@EnableMethodSecurity
public class SecurityConfigWithMethodLevelAuthorization {

    public static final String[] PUBLIC_URLS = {"/home", "/about", "/contact-us"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Creating PasswordEncoder bean....");
        return new BCryptPasswordEncoder(); // Recommended
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .build();
    }


}
