package com.example.axontest.domain.order.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record ShipOrderCommand(@TargetAggregateIdentifier String orderId){
}
