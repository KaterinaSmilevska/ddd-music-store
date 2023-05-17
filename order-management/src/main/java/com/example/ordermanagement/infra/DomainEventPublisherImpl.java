package com.example.ordermanagement.infra;

import com.example.sharedkernel.domain.events.DomainEvent;
import com.example.sharedkernel.infra.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
@AllArgsConstructor
public class DomainEventPublisherImpl implements DomainEventPublisher {

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
        this.kafkaTemplate.send(event.topic(),event.toJson());
    }

}
