package com.example.axontest.domain.order.exception;

public class DuplicateOrderLineException extends IllegalStateException {
    public DuplicateOrderLineException(String productId) {
        super("Cannot duplicate order line for product identifier [" + productId + "]");
    }
}
