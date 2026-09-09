package com.example.securityexercise.controller;

import com.example.securityexercise.domain.Order;
import com.example.securityexercise.dto.CreateOrderRequest;
import com.example.securityexercise.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderRequest request, Authentication authentication) {
        String username = authentication.getName();
        return orderService.createOrder(username, request);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @PutMapping("/{id}/cancel")
    public void cancelOrder(@PathVariable Long id) {
        orderService.cancelAnyOrder(id);
    }

    @PutMapping("/{id}/refund")
    public void refundOrder(@PathVariable Long id) {
        orderService.refundOrder(id);
    }

    @PutMapping("/{id}/status")
    public void updateStatus(@PathVariable Long id, @RequestBody Map<String, String> statusUpdate) {
        String status = statusUpdate.get("status");
        orderService.updateOrderStatus(id, status);
    }
}