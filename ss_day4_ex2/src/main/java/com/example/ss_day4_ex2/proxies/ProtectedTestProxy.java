package com.example.ss_day4_ex2.proxies;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ProtectedTestProxy {

    private final RestClient client;

    public ProtectedTestProxy(RestClient client) {
        this.client = client;
    }

    public String callProtectedTestEndpoint() {
        return client.get()
                .uri("/protected/test")
                .retrieve()
                .body(String.class);
    }
}
