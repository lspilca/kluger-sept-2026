package com.example.securityexercise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.oauth2ResourceServer(
           c -> c.jwt(
                   j -> j.jwkSetUri("http://localhost:8080/oauth2/jwks")
           )
        );

        http.authorizeHttpRequests(
                auth -> auth.requestMatchers(GET,"/api/products").permitAll()
                            .requestMatchers("/api/admin/**").hasRole("ADMIN")
                            .requestMatchers(POST, "/api/orders").authenticated()
                            .requestMatchers(GET, "/api/orders/**").authenticated()
                            .requestMatchers("/api/**").authenticated()
                            .requestMatchers("/protected/**").authenticated()
                            .anyRequest().denyAll()
        );

        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails alice = User.withUsername("alice")
//            .password("{noop}password")
//            .roles("CUSTOMER")
//            .build();
//
//        UserDetails bob = User.withUsername("bob")
//            .password("{noop}password")
//            .roles("CUSTOMER")
//            .authorities("ROLE_CUSTOMER", "ORDER_REFUND")
//            .build();
//
//        UserDetails manager = User.withUsername("manager")
//            .password("{noop}password")
//            .roles("MANAGER")
//            .build();
//
//        UserDetails admin = User.withUsername("admin")
//            .password("{noop}password")
//            .roles("ADMIN")
//            .build();
//
//        return new InMemoryUserDetailsManager(alice, bob, manager, admin);
//    }
}