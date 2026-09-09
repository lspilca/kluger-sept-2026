package com.example.securityexercise.repository;

import com.example.securityexercise.domain.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class ProductRepository {

    private final List<Product> products = new CopyOnWriteArrayList<>();

    public ProductRepository() {
        products.add(new Product(1L, "Laptop", new BigDecimal("1200.00")));
        products.add(new Product(2L, "Smartphone", new BigDecimal("800.00")));
        products.add(new Product(3L, "Headphones", new BigDecimal("150.00")));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.id().equals(id)).findFirst();
    }

    public Product save(Product product) {
        products.add(product);
        return product;
    }

    public void deleteById(Long id) {
        products.removeIf(p -> p.id().equals(id));
    }
}