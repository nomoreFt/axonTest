package com.example.axontest.domain.order.aggregate;

import com.example.axontest.domain.order.command.CreateOrderCommand;
import com.example.axontest.domain.order.command.ShipOrderCommand;
import com.example.axontest.domain.order.event.OrderConfirmedEvent;
import com.example.axontest.domain.order.event.OrderCreatedEvent;
import com.example.axontest.domain.order.event.OrderShippedEvent;
import com.example.axontest.domain.order.exception.UnconfirmedOrderException;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderAggregateTest {
    private FixtureConfiguration<OrderAggregate> fixture;

    @BeforeEach
    public void setUp() {
        fixture = new AggregateTestFixture<>(OrderAggregate.class);
    }

    @Test
    @DisplayName("Order가 Create시에 event 생성")
    void createOrder() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.givenNoPriorActivity()
                .when(new CreateOrderCommand(orderId, product))
                .expectEvents(new OrderCreatedEvent(orderId, product));

    }

    @Test
    @DisplayName("Order 주문이 확인되지 않은경우 주문을 배송할 수 없음 Exception")
    void shipOrder() {
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderCreatedEvent(orderId, product))
                .when(new ShipOrderCommand(orderId))
                .expectException(UnconfirmedOrderException.class);
    }

    @Test
    @DisplayName("Order 주문이 확인된 경우 주문을 배송할 수 있음")
    void shipOrder2(){
        String orderId = UUID.randomUUID().toString();
        String product = "Deluxe Chair";
        fixture.given(new OrderCreatedEvent(orderId, product),
                new OrderConfirmedEvent(orderId))
                .when(new ShipOrderCommand(orderId))
                .expectEvents(new OrderShippedEvent(orderId));

    }


}