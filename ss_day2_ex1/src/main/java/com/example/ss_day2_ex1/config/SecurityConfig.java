package com.example.ss_day2_ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity // @EnableGlobalMethodSecurity  @Secure, @RoleAllowed
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests(                              // authorizeRequests
//          a ->  a.requestMatchers("/admin/*/api/**").hasAuthority("write")   // antMatchers(), mvcMatchers(), regexMatchers()  ->  /demo, /demo/
//                  .requestMatchers("/admin/**/api/**").hasRole("ADMIN")  // ROLE_ADMIN
//                  .requestMatchers(HttpMethod.GET).hasAuthority("read")
//                  .requestMatchers(HttpMethod.POST, "/admin/**").hasAuthority("write")
//                  .anyRequest().denyAll()
            a -> a.requestMatchers("/api/**").authenticated()   // -> authorization
                    .requestMatchers(HttpMethod.GET, "/").permitAll()  // -> javascript, css, html, images
                    .anyRequest().denyAll()
        );

        http.csrf(c -> c.disable());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
