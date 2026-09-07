package com.example.ss_day1_ex2.config;

import com.example.ss_day1_ex2.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {

    @Value("${cors.allowed.origin}")
    private String allowedOrigins;
    @Value("${cors.allowed.method}")
    private String allowedMethods; // POST, PUT
    @Value("${cors.allowed.header}")
    private String allowedHeaders;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.httpBasic(Customizer.withDefaults())
            .formLogin(Customizer.withDefaults());

        http.authorizeHttpRequests(a -> a.anyRequest().authenticated());

        // CSRF - Cross Script Request Forgery
        // http.csrf(c -> c.disable());

        // CORS -> Cross Origin Resource Sharing
//        http.cors(c -> c.configure())
        // abc.com -> xyz.com
        // abc.com -> api.abc.com
        // localhost:3210 -> localhost:8080
        // localhost:8080 -> 127.0.0.1:8080

        http.cors(c -> c.configurationSource(new CustomCorsConfigurationSource(allowedOrigins, allowedMethods, allowedHeaders)));

        return http.build();


//        return http.httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(a -> a.anyRequest().authenticated())
//                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
