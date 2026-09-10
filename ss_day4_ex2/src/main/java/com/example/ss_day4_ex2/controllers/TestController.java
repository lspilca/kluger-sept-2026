package com.example.ss_day4_ex2.controllers;

import com.example.ss_day4_ex2.proxies.ProtectedTestProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final ProtectedTestProxy protectedTestProxy;

    public TestController(ProtectedTestProxy protectedTestProxy) {
        this.protectedTestProxy = protectedTestProxy;
    }

    @GetMapping("/test")
    public String test() {
        return protectedTestProxy.callProtectedTestEndpoint();
    }
}
