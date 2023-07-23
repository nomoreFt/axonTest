package com.example.axontest.domain.order.event;

public record OrderCreatedEvent(String orderId, String productId) {
}
