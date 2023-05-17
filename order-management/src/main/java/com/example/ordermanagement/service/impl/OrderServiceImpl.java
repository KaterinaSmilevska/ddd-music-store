package com.example.ordermanagement.service.impl;

import com.example.ordermanagement.domain.exceptions.OrderIdNotExistException;
import com.example.ordermanagement.domain.exceptions.OrderItemIdNotExistException;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import com.example.ordermanagement.domain.model.OrderItemId;
import com.example.ordermanagement.domain.repository.OrderRepository;
import com.example.ordermanagement.service.OrderService;
import com.example.ordermanagement.service.forms.OrderForm;
import com.example.ordermanagement.service.forms.OrderItemForm;
import com.example.sharedkernel.domain.events.orders.OrderItemCreated;
import com.example.sharedkernel.infra.DomainEventPublisher;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final Validator validator;

    @Override
    public OrderId placeOrder(OrderForm orderForm) {
        Objects.requireNonNull(orderForm, "order must not be null.");
        var constraintViolations = validator.validate(orderForm);
        if (constraintViolations.size() > 0) {
            throw new ConstraintViolationException("The order form is not valid", constraintViolations);
        }
        var newOrder = orderRepository.saveAndFlush(toDomainObject(orderForm));
        newOrder.getOrderItems().forEach(item -> domainEventPublisher
                .publish(new OrderItemCreated(item.getProductId().getId(), item.getQuantity())));
        return newOrder.getId();

    }

    @Override
    public List<Order> findAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return this.orderRepository.findById(id);
    }

    @Override
    public void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.addItem(orderItemForm.getMusicalInstrument(), orderItemForm.getQuantity());
        orderRepository.saveAndFlush(order);
        domainEventPublisher.publish(new OrderItemCreated(orderItemForm.getMusicalInstrument().getId().getId(), orderItemForm.getQuantity()));
    }

    @Override
    public void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNotExistException {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderIdNotExistException::new);
        order.removeItem(orderItemId);
        orderRepository.saveAndFlush(order);
    }

    private Order toDomainObject(OrderForm orderForm) {
        var order = new Order(Instant.now(), orderForm.getCurrency());
        orderForm.getItems().forEach(item -> order.addItem(item.getMusicalInstrument(), item.getQuantity()));
        return order;
    }

}
