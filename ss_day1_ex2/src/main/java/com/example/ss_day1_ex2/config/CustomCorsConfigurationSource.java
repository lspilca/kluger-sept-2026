package com.example.ss_day1_ex2.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


@AllArgsConstructor
public class CustomCorsConfigurationSource implements CorsConfigurationSource {

    private final String allowedOrigin;
    private final String allowedMethods;
    private final String allowedHeader;

    @Override
    public @Nullable CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin(allowedOrigin);
        corsConfiguration.addAllowedMethod(allowedMethods);
        corsConfiguration.addAllowedHeader(allowedHeader);

        return corsConfiguration;
    }
}
