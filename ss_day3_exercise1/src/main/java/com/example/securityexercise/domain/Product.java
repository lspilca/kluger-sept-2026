package com.example.securityexercise.domain;

import java.math.BigDecimal;

public record Product(
    Long id,
    String name,
    BigDecimal price
) {}