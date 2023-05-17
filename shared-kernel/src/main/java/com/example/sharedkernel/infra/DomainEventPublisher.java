package com.example.sharedkernel.infra;

import com.example.sharedkernel.domain.events.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}
