package com.example.securityexercise.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@TestConfiguration
public class UsersConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        var u1 = User.builder().username("admin").password("bob").roles("ADMIN").build();
        var u2 = User.builder().username("manager").password("bob").roles("MANAGER").build();
        var u3 = User.builder().username("client").password("bob").roles("CLIENT").build();
        var u4 = User.builder().username("refund").password("bob").authorities("ORDER_REFUND").build();
        return new InMemoryUserDetailsManager(u1,u2,u3,u4);
    }
}
