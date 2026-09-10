package com.example.securityexercise.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminEndpointsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1() throws Exception {
        mockMvc.perform(get("/api/admin/reports"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    public void test2() throws Exception {
        mockMvc.perform(get("/api/admin/reports"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void test3() throws Exception {
        mockMvc.perform(get("/api/admin/reports"))
                .andExpect(status().isOk());
    }
}
