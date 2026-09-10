package com.example.securityexercise.controller;

import com.example.securityexercise.config.UsersConfig;
import com.example.securityexercise.domain.Order;
import com.example.securityexercise.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(UsersConfig.class)
public class OrderEndpointsControllerWUDTest {

    @MockitoBean
    private OrderRepository orderRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test1() throws Exception {
        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails("admin")
    public void test2() throws Exception {
        Order order = new Order(1L, "bob", "beer", BigDecimal.TEN, "new");
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        mockMvc.perform(get("/api/orders/1"))
                .andExpect(status().isOk());
    }
}
