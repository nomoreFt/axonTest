package com.example.axontest.domain.order.event;

public record ProductCountDecrementedEvent(String orderId, String productId) {
}
