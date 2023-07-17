package com.stateful.spring.security.config;

import com.stateful.spring.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String FETCH_USER_QUERY_BY_USER_NAME_QUERY = "select username, password, enabled from users where username=?";
    public static final String FETCH_ROLE_BY_USER_NAME_QUERY = "select username, role from users where username=?";
    public static final String[] PUBLIC_URLS = {"/home", "/about", "/contact-us"};

    private final DataSource dataSource;

    private final UserDetailsServiceImpl userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("abc").password("abc123").authorities("CUSTOMER")
//                .and()
//                .withUser("def").password("def123").authorities("ADMIN")
//                .and()
//                .passwordEncoder(passwordEncoder());

        /**
         * jdbcAuthentication cares about only sql query result set, no need to create user class
         */
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery(FETCH_USER_QUERY_BY_USER_NAME_QUERY)
//                .authoritiesByUsernameQuery(FETCH_ROLE_BY_USER_NAME_QUERY);

//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery(FETCH_USER_QUERY_BY_USER_NAME_QUERY)
//                .authoritiesByUsernameQuery(FETCH_ROLE_BY_USER_NAME_QUERY);

        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());





    }

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance(); // NoOpPasswordEncoder - Not recommended
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); // Recommended
    }

    // authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(PUBLIC_URLS).permitAll()
                /**
                 * hasAuthority also means authentication is required before checking role
                 */
                .antMatchers("/bank-setting").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin() // This is for browser MVC application
                .and()
                .httpBasic(); // This is for rest api call

    }

}
