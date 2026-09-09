package com.example.securityexercise.repository;

import com.example.securityexercise.domain.Order;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {

    private final List<Order> orders = new CopyOnWriteArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(4);

    public OrderRepository() {
        orders.add(new Order(1L, "alice", "Laptop", new BigDecimal("1200.00"), "PLACED"));
        orders.add(new Order(2L, "bob", "Smartphone", new BigDecimal("800.00"), "PLACED"));
        orders.add(new Order(3L, "alice", "Headphones", new BigDecimal("150.00"), "SHIPPED"));
    }

    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    public Optional<Order> findById(Long id) {
        return orders.stream().filter(o -> o.id().equals(id)).findFirst();
    }

    public Order save(Order order) {
        if (order.id() == null) {
            Order newOrder = new Order(
                idGenerator.getAndIncrement(),
                order.ownerUsername(),
                order.product(),
                order.total(),
                order.status()
            );
            orders.add(newOrder);
            return newOrder;
        } else {
            orders.removeIf(o -> o.id().equals(order.id()));
            orders.add(order);
            return order;
        }
    }

    public void deleteById(Long id) {
        orders.removeIf(o -> o.id().equals(id));
    }
}