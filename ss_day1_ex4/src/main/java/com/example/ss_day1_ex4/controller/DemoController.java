package com.example.ss_day1_ex4.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping
    public String demo(Authentication authentication) {
        var a = SecurityContextHolder.getContext().getAuthentication();
        return "Demo! " + authentication;
    }
}
