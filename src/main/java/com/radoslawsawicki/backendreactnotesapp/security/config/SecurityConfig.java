package com.radoslawsawicki.backendreactnotesapp.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails admin = User.builder()
                .username("radek")
                .password("{noop}kas123")
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}user")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests(configurer ->
                configurer
                        .requestMatchers("/*").hasRole("ADMIN")
                        .requestMatchers("/*").hasRole("USER")
                        .anyRequest().authenticated())
                .formLogin(form ->
                        form
                                .loginPage("/showMyLoginPages")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll());
        return http.build();
    }
}