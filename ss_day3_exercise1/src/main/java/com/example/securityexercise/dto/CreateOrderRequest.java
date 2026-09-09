package com.example.securityexercise.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(
    String product,
    BigDecimal total
) {}