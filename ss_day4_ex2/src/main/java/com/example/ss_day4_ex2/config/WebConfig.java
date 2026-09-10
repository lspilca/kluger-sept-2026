package com.example.ss_day4_ex2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.client.OAuth2ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class WebConfig {

    @Bean
    RestClient restClient(OAuth2AuthorizedClientManager auth2AuthorizedClientManager) {
        OAuth2ClientHttpRequestInterceptor interceptor = new OAuth2ClientHttpRequestInterceptor(auth2AuthorizedClientManager);

        interceptor.setClientRegistrationIdResolver(c -> "client");

        return RestClient.builder()
                .requestInterceptor(interceptor)
                .baseUrl("http://localhost:7070")
                .build();
    }

    @Bean
    RestClient restClient2(OAuth2AuthorizedClientManager auth2AuthorizedClientManager) {
        OAuth2ClientHttpRequestInterceptor interceptor = new OAuth2ClientHttpRequestInterceptor(auth2AuthorizedClientManager);

        interceptor.setClientRegistrationIdResolver(c -> "client2");

        return RestClient.builder()
                .requestInterceptor(interceptor)
                .baseUrl("http://localhost:7070")
                .build();
    }

    @Bean
    OAuth2AuthorizedClientManager  oAuth2AuthorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository, // application.properties
            OAuth2AuthorizedClientService oAuth2AuthorizedClientService
    ) {
        OAuth2AuthorizedClientProvider provider =  OAuth2AuthorizedClientProviderBuilder
                .builder()
                .clientCredentials()
                .build();

        AuthorizedClientServiceOAuth2AuthorizedClientManager clientManager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(clientRegistrationRepository, oAuth2AuthorizedClientService);

        clientManager.setAuthorizedClientProvider(provider);

        return clientManager;
    }


}
