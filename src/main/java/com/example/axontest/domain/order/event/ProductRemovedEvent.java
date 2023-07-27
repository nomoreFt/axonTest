package com.example.axontest.domain.order.event;

public record ProductRemovedEvent(String orderId, String productId) {
}
