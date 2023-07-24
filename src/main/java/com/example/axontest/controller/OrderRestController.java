package com.example.axontest.controller;

import com.example.axontest.domain.order.Order;
import com.example.axontest.domain.order.command.ConfirmOrderCommand;
import com.example.axontest.domain.order.command.CreateOrderCommand;
import com.example.axontest.domain.order.command.ShipOrderCommand;
import com.example.axontest.domain.order.query.FindAllOrderedProductsQuery;
import com.example.axontest.domain.order.query.FindOrderByIdQuery;
import com.example.axontest.domain.order.query.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class OrderRestController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @GetMapping("/ship-order")
    public CompletableFuture<Void> shipOrder() {
        String orderId = UUID.randomUUID().toString();
        return commandGateway.send(new CreateOrderCommand(orderId, "Deluxe Chair"))
                .thenCompose(result -> commandGateway.send(new ConfirmOrderCommand(orderId)))
                .thenCompose(result -> commandGateway.send(new ShipOrderCommand(orderId)));
    }

    @GetMapping("/all-orders")
    public CompletableFuture<Order> findAllOrders() {
        CompletableFuture<Order> query = queryGateway.query(new FindAllOrderedProductsQuery(), ResponseTypes.instanceOf(Order.class));
        return query;
    }



}
