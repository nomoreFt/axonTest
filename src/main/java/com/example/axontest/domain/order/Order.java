package com.example.axontest.domain.order;

import java.io.Serializable;

public class Order implements Serializable {

    private final String orderId;
    private final String productId;
    private OrderStatus orderStatus;

    public Order(String orderId, String productId) {
        this.orderId = orderId;
        this.productId = productId;
        orderStatus = OrderStatus.CREATED;
    }

    public void setOrderConfirmed() {
        this.orderStatus = OrderStatus.CONFIRMED;
    }

    public void setOrderShipped() {
        this.orderStatus = OrderStatus.SHIPPED;
    }

    // getters, equals/hashCode and toString functions
}
