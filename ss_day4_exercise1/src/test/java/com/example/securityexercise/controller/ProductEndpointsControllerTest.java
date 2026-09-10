package com.example.securityexercise.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductEndpointsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }
}
