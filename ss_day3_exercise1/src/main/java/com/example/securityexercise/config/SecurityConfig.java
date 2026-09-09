package com.example.securityexercise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails alice = User.withUsername("alice")
            .password("{noop}password")
            .roles("CUSTOMER")
            .build();

        UserDetails bob = User.withUsername("bob")
            .password("{noop}password")
            .roles("CUSTOMER")
            .authorities("ROLE_CUSTOMER", "ORDER_REFUND")
            .build();

        UserDetails manager = User.withUsername("manager")
            .password("{noop}password")
            .roles("MANAGER")
            .build();

        UserDetails admin = User.withUsername("admin")
            .password("{noop}password")
            .roles("ADMIN")
            .build();

        return new InMemoryUserDetailsManager(alice, bob, manager, admin);
    }
}