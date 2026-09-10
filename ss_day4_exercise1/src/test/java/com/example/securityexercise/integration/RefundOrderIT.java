package com.example.securityexercise.integration;

import com.example.securityexercise.domain.Order;
import com.example.securityexercise.repository.OrderRepository;
import com.example.securityexercise.service.OrderService;
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
public class RefundOrderIT {

    @MockitoBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void test1() {
        assertThrows(
                AuthenticationException.class,
                () -> orderService.refundOrder(1L)
        );
    }

    @Test
    @WithMockUser(authorities = "ORDER_REFUND")
    public void test2() {
        Order o = mock(Order.class);

        when(orderRepository.findById(1L)).thenReturn(Optional.of(o));

        orderService.refundOrder(1L);
    }

    @Test
    @WithMockUser(authorities = "ORDER_READ")
    public void test3() {
        assertThrows(AuthorizationDeniedException.class,
                () -> orderService.refundOrder(1L));
    }
}
