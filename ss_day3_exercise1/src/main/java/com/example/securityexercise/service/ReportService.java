package com.example.securityexercise.service;

import com.example.securityexercise.domain.Order;
import com.example.securityexercise.dto.SalesReport;
import com.example.securityexercise.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReportService {

    private final OrderRepository orderRepository;

    public ReportService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public SalesReport getSalesReport() {
        List<Order> orders = orderRepository.findAll();
        BigDecimal totalRevenue = orders.stream()
                .map(Order::total)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new SalesReport(orders.size(), totalRevenue);
    }
}