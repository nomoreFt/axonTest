package com.example.axontest.domain.order.aggregate;

import com.example.axontest.domain.order.OrderLine;
import com.example.axontest.domain.order.command.AddProductCommand;
import com.example.axontest.domain.order.command.ConfirmOrderCommand;
import com.example.axontest.domain.order.command.CreateOrderCommand;
import com.example.axontest.domain.order.command.ShipOrderCommand;
import com.example.axontest.domain.order.event.*;
import com.example.axontest.domain.order.exception.DuplicateOrderLineException;
import com.example.axontest.domain.order.exception.OrderAlreadyConfirmedException;
import com.example.axontest.domain.order.exception.UnconfirmedOrderException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashMap;
import java.util.Map;

import static org.axonframework.modelling.command.AggregateLifecycle.*;


@Aggregate(snapshotTriggerDefinition = "orderAggregateSnapshotTriggerDefinition")
//protected NoArgs
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;

    @AggregateMember
    private Map<String,OrderLine> orderLines;

    @CommandHandler
    public OrderAggregate(CreateOrderCommand command){
        apply(
                new OrderCreatedEvent(command.orderId(), command.product()));

    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event){
        this.orderId = event.orderId();
        this.orderConfirmed = false;
        this.orderLines = new HashMap<>();
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        if (orderConfirmed) {
            return;
        }
        apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) {
        if (!orderConfirmed) {
            throw new UnconfirmedOrderException();
        }
        apply(new OrderShippedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        orderConfirmed = true;
    }

    @CommandHandler
    public void handle(AddProductCommand command){
        if (orderConfirmed) {
            throw new OrderAlreadyConfirmedException(orderId);
        }

        String productId = command.productId();
        if(orderLines.containsKey(productId)){
            throw new DuplicateOrderLineException(productId);
        }
        AggregateLifecycle.apply(new ProductAddedEvent(orderId, productId));
    }

    @EventSourcingHandler
    public void on(ProductAddedEvent event){
        String productId = event.productId();
        this.orderLines.put(productId, new OrderLine(productId));
    }

    @EventSourcingHandler
    public void on(ProductRemovedEvent event) {
        String productId = event.productId();
        this.orderLines.remove(productId);
    }
}
