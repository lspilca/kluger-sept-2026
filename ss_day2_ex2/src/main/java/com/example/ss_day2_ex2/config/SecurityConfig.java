package com.example.ss_day2_ex2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
                a -> a.jwt(j -> j.jwkSetUri("http://localhost:8080/realms/master/protocol/openid-connect/certs"))
        );

        http.authorizeHttpRequests(
                a -> a.anyRequest().authenticated()
        );

        return http.build();
    }
}
