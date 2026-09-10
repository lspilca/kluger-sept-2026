package com.example.ss_day4_ex1.services;

import com.example.ss_day4_ex1.entities.Client;
import com.example.ss_day4_ex1.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegisteredClientService implements RegisteredClientRepository {

    private final ClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient) {
        // omitted
    }

    @Override
    public @Nullable RegisteredClient findById(String id) {
        return mapToRegisteredClient(
                clientRepository.findById(UUID.fromString(id))
                        .orElseThrow()
        );
    }

    @Override
    public @Nullable RegisteredClient findByClientId(String clientId) {
        return mapToRegisteredClient(
                clientRepository.findByClientId(clientId)
                        .orElseThrow()
        );
    }

    private RegisteredClient mapToRegisteredClient(Client client) {
        return RegisteredClient.withId(client.getId().toString())
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .scope(client.getScope())
                .redirectUri(client.getRedirectUri())
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofDays(1)).build())
                .authorizationGrantTypes(authorizationGrantTypes -> {
                    authorizationGrantTypes.addAll(
                      client.getGrantTypes().stream()
                              .map(g -> new AuthorizationGrantType(g.getGrantType()))
                              .collect(Collectors.toSet())
                    );
                }).build();
    }
}
