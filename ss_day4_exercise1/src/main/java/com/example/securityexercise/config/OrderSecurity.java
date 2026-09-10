package com.example.securityexercise.config;

import com.example.securityexercise.domain.Order;
import com.example.securityexercise.repository.OrderRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("orderSecurity")
public class OrderSecurity {

    private final OrderRepository orderRepository;

    public OrderSecurity(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean isOwnerOrHasRoleAdmin(Long orderId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        boolean ownsOrder = orderRepository.findById(orderId)
                .map(Order::ownerUsername)
                .map(owner -> owner.equals(username))
                .orElse(false);

        return ownsOrder || authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public boolean canUpdateOrder(String status) {
        var authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return List.of("PROCESSING", "SHIPPED", "CANCELLED").contains(status);
        }

        if (authorities.contains(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
            return List.of("PROCESSING", "SHIPPED").contains(status);
        }

        return false;
    }
}