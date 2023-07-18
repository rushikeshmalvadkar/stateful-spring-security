package com.stateful.spring.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Slf4j
public class SecurityConfigWithoutWebSecurityConfigureAdapter {

    public static final String[] PUBLIC_URLS = {"/home", "/about", "/contact-us"};

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Creating PasswordEncoder bean....");
        return new BCryptPasswordEncoder(); // Recommended
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                .antMatchers("/account-info" ,"/bank-setting").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .build();

    }


}
