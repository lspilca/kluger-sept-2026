package com.example.securityexercise.integration;

import com.example.securityexercise.domain.Order;
import com.example.securityexercise.repository.OrderRepository;
import com.example.securityexercise.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CancelAnyOrderIT {

    @MockitoBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    @DisplayName("""
            WHEN cancelling an order
            IF no authentication is provided
            THEN an exception is thrown
            """)
    void test1() {
        assertThrows(AuthenticationException.class,
                () -> orderService.cancelAnyOrder(1L));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void test2() {
        Order o = mock(Order.class);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(o));

        orderService.cancelAnyOrder(1L);
    }

    @Test
    @WithMockUser(roles = "MANAGER")
    void test3() {
        Order o = mock(Order.class);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(o));

        orderService.cancelAnyOrder(1L);
    }

    @Test
    @WithMockUser(roles = "CUSTOMER")
    void test4() {
        assertThrows(AuthorizationDeniedException.class,
                () -> orderService.cancelAnyOrder(1L));
    }

}
