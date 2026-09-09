package com.example.securityexercise.domain;

import java.math.BigDecimal;

public record Order(
    Long id,
    String ownerUsername,
    String product,
    BigDecimal total,
    String status
) {}