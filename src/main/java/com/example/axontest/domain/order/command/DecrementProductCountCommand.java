package com.example.axontest.domain.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record DecrementProductCountCommand(@TargetAggregateIdentifier String orderId, String productId) {
}
