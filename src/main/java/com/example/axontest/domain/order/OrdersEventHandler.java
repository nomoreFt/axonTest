package com.example.axontest.domain.order;

import com.example.axontest.domain.order.Order;
import com.example.axontest.domain.order.event.OrderConfirmedEvent;
import com.example.axontest.domain.order.event.OrderCreatedEvent;
import com.example.axontest.domain.order.event.OrderShippedEvent;
import com.example.axontest.domain.order.query.FindAllOrderedProductsQuery;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrdersEventHandler {

    private final Map<String, Order> orders = new HashMap<>();

    @EventHandler
    public void on(OrderCreatedEvent event) {
        String orderId = event.orderId();
        orders.put(orderId, new Order(orderId, event.productId()));
    }

    @EventHandler
    public void on(OrderConfirmedEvent event) {
        String orderId = event.orderId();
        orders.get(orderId).setOrderConfirmed();
    }

    @EventHandler
    public void on(OrderShippedEvent event) {
        String orderId = event.orderId();
        orders.get(orderId).setOrderShipped();
    }

    @QueryHandler
    public List<Order> handle(FindAllOrderedProductsQuery query) {
        return new ArrayList<>(orders.values());
    }
}
