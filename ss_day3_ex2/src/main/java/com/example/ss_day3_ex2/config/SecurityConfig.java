package com.example.ss_day3_ex2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerAuthenticationManagerResolver;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.oauth2ResourceServer(a -> a.jwt(
//                j -> j.jwkSetUri("http://localhost:8080/oauth2/jwks")
//                      .jwtAuthenticationConverter(jwtAuthenticationConverter())
//        ));

        http.oauth2ResourceServer(a -> a.authenticationManagerResolver(
                JwtIssuerAuthenticationManagerResolver
                        .fromTrustedIssuers(
                                "http://localhost:7070/realms/master",
                                "http://localhost:8080")
        ));

        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated());

        return http.build();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();

        converter.setAuthoritiesClaimName("authorities");
        converter.setAuthorityPrefix("");

        JwtAuthenticationConverter authenticationConverter = new  JwtAuthenticationConverter();
        authenticationConverter.setJwtGrantedAuthoritiesConverter(converter);

        return authenticationConverter;
    }
}
