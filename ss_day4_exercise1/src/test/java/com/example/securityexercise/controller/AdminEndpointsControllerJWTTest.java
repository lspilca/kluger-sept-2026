package com.example.securityexercise.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminEndpointsControllerJWTTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1() throws Exception {
        mockMvc.perform(get("/api/admin/reports"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void test2() throws Exception {
        mockMvc.perform(get("/api/admin/reports")
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_CUSTOMER"))))
                .andExpect(status().isForbidden());
    }

    @Test
    public void test3() throws Exception {
        mockMvc.perform(get("/api/admin/reports")
                        .with(jwt().authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))))
                .andExpect(status().isOk());
    }

    @Test
    public void test4() throws Exception {
        mockMvc.perform(get("/api/admin/reports")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer eyJraWQiOiIxMjkyZDI2OC05NTUyLTRkZjUtODU5Mi05MjdmNTA0MWIwMWMiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJib2IiLCJhdWQiOiJjbGllbnQiLCJuYmYiOjE3ODkwMzEyMTYsInNjb3BlIjpbIm9wZW5pZCJdLCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgwODAiLCJleHAiOjE3ODkxMTc2MTYsImlhdCI6MTc4OTAzMTIxNiwianRpIjoiYjVjNTlmNmMtYmViOS00OGVkLThlMDctOTk3Y2I3ZjI3N2M1In0.PY9S525dfQ-B5r7WG2pxpnV9v5fgPjECn5Che83AecSDvN3FOncYZV9LnzWsUhg5rESbVndq6EOuvwPmOFcPlTIItjfqmN-_5GwVp9Uqh933ggICwrkPXQKSeHlXvQGJmyw42dFS6xXxIt5L1rvJqM-XUxZ2J4MO9rXByYNPCggSZtNe1CkVrqnRFE4DpjHXG-wTRcoZIly5ujM2gktbxMVydrYd__fEvJ5SVuhytBBFUtS1zjDJIMPbzGefcT4Gcl3jAxBcft8wZjgq3yf67pi8O_GkLgYHXMvKXe-jX9sWbTYHAMqYyNkI__QzWoVv4YQ2v1UYNyweSI5eTnjQ0g"))
                .andExpect(status().isOk());
    }
}
