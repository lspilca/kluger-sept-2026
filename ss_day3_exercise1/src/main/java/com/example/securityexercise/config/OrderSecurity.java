package com.example.securityexercise.config;

import com.example.securityexercise.domain.Order;
import com.example.securityexercise.repository.OrderRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("orderSecurity")
public class OrderSecurity {

    private final OrderRepository orderRepository;

    public OrderSecurity(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean isOwner(Long orderId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }
        String username = authentication.getName();
        return orderRepository.findById(orderId)
                .map(Order::ownerUsername)
                .map(owner -> owner.equals(username))
                .orElse(false);
    }
}