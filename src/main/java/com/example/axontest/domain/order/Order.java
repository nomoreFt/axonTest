package com.example.axontest.domain.order;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
public class Order {

    private final String orderId;
    private final String productId;
    private OrderStatus orderStatus;

    @JsonCreator
    public Order(@JsonProperty("orderId")String orderId,@JsonProperty("productId") String productId) {
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
