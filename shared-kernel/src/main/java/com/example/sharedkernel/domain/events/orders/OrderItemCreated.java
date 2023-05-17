package com.example.sharedkernel.domain.events.orders;

import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.config.TopicHolder;
import lombok.Getter;

@Getter
public class OrderItemCreated extends DomainEvent {

    private String productId;
    private int quantity;

    public OrderItemCreated(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
    }

    public OrderItemCreated(String productId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_CREATED);
        this.productId = productId;
        this.quantity = quantity;
    }

}
