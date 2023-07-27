package com.example.axontest.domain.order;

import com.example.axontest.domain.order.exception.OrderAlreadyConfirmedException;
import com.example.axontest.domain.order.command.DecrementProductCountCommand;
import com.example.axontest.domain.order.command.IncrementProductCountCommand;
import com.example.axontest.domain.order.event.OrderConfirmedEvent;
import com.example.axontest.domain.order.event.ProductCountDecrementedEvent;
import com.example.axontest.domain.order.event.ProductCountIncrementedEvent;
import com.example.axontest.domain.order.event.ProductRemovedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.EntityId;

public class OrderLine {

    @EntityId
    private final String productId;
    private Integer count;
    private boolean orderConfirmed;

    public OrderLine(String productId) {
        this.productId = productId;
        this.count = 1;
    }

    @CommandHandler
    public void handle(DecrementProductCountCommand command) {
        if (orderConfirmed) {
            throw new OrderAlreadyConfirmedException(command.orderId());
        }
        if (count <= 1) {
            AggregateLifecycle.apply(new ProductRemovedEvent(command.orderId(), command.productId()));
        } else {
            AggregateLifecycle.apply(new ProductCountDecrementedEvent(command.orderId(), command.productId()));
        }
    }

    @CommandHandler
    public void handle(IncrementProductCountCommand command) {
        if (orderConfirmed) {
            throw new OrderAlreadyConfirmedException(command.orderId());
        }
        AggregateLifecycle.apply(new ProductCountIncrementedEvent(command.orderId(), command.productId()));
    }

    @EventSourcingHandler
    public void on(ProductCountIncrementedEvent event) {
        this.count++;
    }

    @EventSourcingHandler
    public void on(ProductCountDecrementedEvent event) {
        this.count--;
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        this.orderConfirmed = true;
    }
}
