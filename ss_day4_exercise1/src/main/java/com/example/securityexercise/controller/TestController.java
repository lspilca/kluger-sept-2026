package com.example.securityexercise.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/protected/test")
    public String test(Authentication authentication) {
        return authentication.toString();
    }
}
