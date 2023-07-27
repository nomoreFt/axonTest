package com.example.axontest.domain.order;

import com.example.axontest.domain.order.Order;
import com.example.axontest.domain.order.event.*;
import com.example.axontest.domain.order.query.FindAllOrderedProductsQuery;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface OrdersEventHandler {

        void on(OrderCreatedEvent event);

        void on(ProductAddedEvent event);

        void on(ProductCountIncrementedEvent event);

        void on(ProductCountDecrementedEvent event);

        void on(ProductRemovedEvent event);

        void on(OrderConfirmedEvent event);

        void on(OrderShippedEvent event);

        List<Order> handle(FindAllOrderedProductsQuery query);

        /*Publisher<Order> handleStreaming(FindAllOrderedProductsQuery query);

        Integer handle(TotalProductsShippedQuery query);

        Order handle(OrderUpdatesQuery query);

        void reset(List<Order> orderList);*/
    }

