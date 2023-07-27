package com.example.axontest.domain.order.event;

public record ProductAddedEvent(String orderId, String productId) {
}
