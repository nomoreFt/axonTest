package com.example.axontest.domain.order.aggregate;

import com.example.axontest.domain.order.command.ConfirmOrderCommand;
import com.example.axontest.domain.order.command.CreateOrderCommand;
import com.example.axontest.domain.order.command.ShipOrderCommand;
import com.example.axontest.domain.order.event.OrderConfirmedEvent;
import com.example.axontest.domain.order.event.OrderCreatedEvent;
import com.example.axontest.domain.order.event.OrderShippedEvent;
import com.example.axontest.domain.order.exception.UnconfirmedOrderException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.*;

@Aggregate
//protected NoArgs
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;


    @CommandHandler
    public OrderAggregate(CreateOrderCommand command){
        apply(
                new OrderCreatedEvent(command.orderId(), command.product()));

    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event){
        this.orderId = event.orderId();
        this.orderConfirmed = false;
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
}
