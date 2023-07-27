package com.example.axontest.domain.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record IncrementProductCountCommand(@TargetAggregateIdentifier String orderId, String productId) {
}
