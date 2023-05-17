package com.example.productcatalog.xport.events;

import com.example.productcatalog.domain.models.ProductId;
import com.example.productcatalog.services.MusicalInstrumentService;
import com.example.sharedkernel.domain.config.TopicHolder;
import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.domain.events.orders.OrderItemRemoved;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;


@Service
@AllArgsConstructor
public class ProductEventListener {

    private final MusicalInstrumentService musicalInstrumentService;

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_CREATED, groupId = "productCatalog")
    public void consumeOrderItemCreatedEvent(String jsonMessage) {
        try {
            OrderItemCreated event = DomainEvent.fromJson(jsonMessage,OrderItemCreated.class);
            musicalInstrumentService.orderItemCreated(ProductId.of(event.getProductId()), event.getQuantity());
        } catch (Exception e){

        }

    }

    @KafkaListener(topics= TopicHolder.TOPIC_ORDER_ITEM_REMOVED, groupId = "productCatalog")
    public void consumeOrderItemRemovedEvent(String jsonMessage) {
        try {
            OrderItemRemoved event = DomainEvent.fromJson(jsonMessage,OrderItemRemoved.class);
            musicalInstrumentService.orderItemRemoved(ProductId.of(event.getProductId()), event.getQuantity());
        } catch (Exception e){

        }

    }
}
