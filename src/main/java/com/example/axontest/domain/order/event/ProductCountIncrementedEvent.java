package com.example.axontest.domain.order.event;

public record ProductCountIncrementedEvent(String orderId, String productId) {
}
