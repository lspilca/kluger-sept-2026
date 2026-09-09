package com.example.securityexercise.service;

import com.example.securityexercise.domain.Order;
import com.example.securityexercise.dto.CreateOrderRequest;
import com.example.securityexercise.exception.OrderNotFoundException;
import com.example.securityexercise.repository.OrderRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String username, CreateOrderRequest request) {
        Order order = new Order(null, username, request.product(), request.total(), "PLACED");
        return orderRepository.save(order);
    }

    @PreAuthorize("@orderSecurity.isOwnerOrHasRoleAdmin(#orderId)")
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public void cancelAnyOrder(Long orderId) {
        Order order = getOrder(orderId);
        Order updatedOrder = new Order(order.id(), order.ownerUsername(), order.product(), order.total(), "CANCELLED");
        orderRepository.save(updatedOrder);
    }

    @PreAuthorize("hasAuthority('ORDER_REFUND')")
    public void refundOrder(Long orderId) {
        Order order = getOrder(orderId);
        Order updatedOrder = new Order(order.id(), order.ownerUsername(), order.product(), order.total(), "REFUNDED");
        orderRepository.save(updatedOrder);
    }

//    @PreAuthorize("""
//       hasAnyRole('ADMIN', 'MANAGER') and
//       (hasRole('MANAGER') ? #status == 'PROCESSING' or #status == 'SHIPPED' :
//        hasRole('ADMIN') ? #status == 'PROCESSING' or #status == 'SHIPPED' or #status == 'CANCELLED' : false
//       )
//    """)
//    @PreAuthorize("hasRole('ADMIN') OR (hasRole('MANAGER') AND #status != 'CANCELLED')")
    @PreAuthorize("@orderSecurity.canUpdateOrder(#status)")
    public void updateOrderStatus(Long orderId, String status) {
        Order order = getOrder(orderId);
        Order updatedOrder = new Order(order.id(), order.ownerUsername(), order.product(), order.total(), status);
        orderRepository.save(updatedOrder);
    }
}