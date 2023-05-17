package com.example.sharedkernel.domain.events.orders;

import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.config.TopicHolder;
import lombok.Getter;

@Getter
public class OrderItemRemoved extends DomainEvent {

    private String productId;
    private int quantity;

    public OrderItemRemoved(String topic) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
    }

    public OrderItemRemoved(String topic, String productId, int quantity) {
        super(TopicHolder.TOPIC_ORDER_ITEM_REMOVED);
        this.productId = productId;
        this.quantity = quantity;
    }

}
