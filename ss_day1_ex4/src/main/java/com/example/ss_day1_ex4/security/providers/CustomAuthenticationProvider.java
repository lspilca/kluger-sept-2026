package com.example.ss_day1_ex4.security.providers;

import com.example.ss_day1_ex4.security.authentication.CustomAuthentication;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${security.key}")
    private String securityKey;

    @Override
    public @Nullable Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication a = (CustomAuthentication)  authentication;
        String securityKey = (String) a.getPrincipal();

        if (securityKey.equals(this.securityKey)) {
            a.setAuthenticated(true);
            return a;
        } else {
            throw new AuthenticationCredentialsNotFoundException("Authentication Failed");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthentication.class);
    }
}
