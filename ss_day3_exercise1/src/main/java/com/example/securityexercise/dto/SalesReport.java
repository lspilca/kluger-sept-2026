package com.example.securityexercise.dto;

import java.math.BigDecimal;

public record SalesReport(
    int totalOrders,
    BigDecimal totalRevenue
) {}