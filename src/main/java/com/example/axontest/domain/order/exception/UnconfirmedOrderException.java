package com.example.axontest.domain.order.exception;

public class UnconfirmedOrderException extends IllegalStateException {
    public UnconfirmedOrderException() {
        super("Cannot ship an order which has not been confirmed yet.");
    }
}
