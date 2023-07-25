package com.example.axontest.domain.order.query;

import com.example.axontest.domain.order.Order;

import java.util.List;

public record OrderResponse(Order order) {
    public OrderResponse(Order order) {
        this.order = order;
    }
}