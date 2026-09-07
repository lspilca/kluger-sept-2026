package com.example.ss_day1_ex4.config;

import com.example.ss_day1_ex4.security.filters.CustomAuthenticationFilter;
import com.example.ss_day1_ex4.security.providers.CustomAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final CustomAuthenticationFilter customAuthenticationFilter;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    public SecurityConfig(CustomAuthenticationFilter customAuthenticationFilter, CustomAuthenticationProvider customAuthenticationProvider) {
        this.customAuthenticationFilter = customAuthenticationFilter;
        this.customAuthenticationProvider = customAuthenticationProvider;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.addFilterAt(customAuthenticationFilter, BasicAuthenticationFilter.class);
        http.authenticationProvider(customAuthenticationProvider);
        http.authorizeHttpRequests(a -> a.anyRequest().authenticated());

        return http.build();
    }
}
