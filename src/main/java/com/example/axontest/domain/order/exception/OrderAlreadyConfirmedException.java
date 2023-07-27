package com.example.axontest.domain.order.exception;

public class OrderAlreadyConfirmedException extends IllegalStateException {

    public OrderAlreadyConfirmedException(String orderId) {
        super("Cannot perform operation because order [" + orderId + "] is already confirmed.");
    }
}
