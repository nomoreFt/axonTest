package com.example.axontest.domain.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateOrderCommand(@TargetAggregateIdentifier String orderId, String product) {
}
