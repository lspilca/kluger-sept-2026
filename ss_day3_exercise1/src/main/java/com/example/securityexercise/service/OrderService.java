package com.example.securityexercise.service;

import com.example.securityexercise.domain.Order;
import com.example.securityexercise.dto.CreateOrderRequest;
import com.example.securityexercise.exception.OrderNotFoundException;
import com.example.securityexercise.repository.OrderRepository;
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

    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

    public void cancelAnyOrder(Long orderId) {
        Order order = getOrder(orderId);
        Order updatedOrder = new Order(order.id(), order.ownerUsername(), order.product(), order.total(), "CANCELLED");
        orderRepository.save(updatedOrder);
    }

    public void refundOrder(Long orderId) {
        Order order = getOrder(orderId);
        Order updatedOrder = new Order(order.id(), order.ownerUsername(), order.product(), order.total(), "REFUNDED");
        orderRepository.save(updatedOrder);
    }

    public void updateOrderStatus(Long orderId, String status) {
        Order order = getOrder(orderId);
        Order updatedOrder = new Order(order.id(), order.ownerUsername(), order.product(), order.total(), status);
        orderRepository.save(updatedOrder);
    }
}