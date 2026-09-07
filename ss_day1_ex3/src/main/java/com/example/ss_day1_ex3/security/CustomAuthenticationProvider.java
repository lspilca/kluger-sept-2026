package com.example.ss_day1_ex3.security;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${security.key}")
    private String securityKey;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken a = (UsernamePasswordAuthenticationToken) authentication;

        if (a.getPrincipal().equals(securityKey)) {
            return new UsernamePasswordAuthenticationToken(securityKey, securityKey, List.of(new SimpleGrantedAuthority("DEFAULT")));
        } else {
            throw new AuthenticationCredentialsNotFoundException("Boom!");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
