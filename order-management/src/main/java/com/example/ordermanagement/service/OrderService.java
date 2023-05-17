package com.example.ordermanagement.service;

import com.example.ordermanagement.domain.exceptions.OrderIdNotExistException;
import com.example.ordermanagement.domain.exceptions.OrderItemIdNotExistException;
import com.example.ordermanagement.domain.model.Order;
import com.example.ordermanagement.domain.model.OrderId;
import com.example.ordermanagement.domain.model.OrderItemId;
import com.example.ordermanagement.service.forms.OrderForm;
import com.example.ordermanagement.service.forms.OrderItemForm;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    OrderId placeOrder(OrderForm orderForm);

    List<Order> findAll();

    Optional<Order> findById(OrderId id);

    void addItem(OrderId orderId, OrderItemForm orderItemForm) throws OrderIdNotExistException;

    void deleteItem(OrderId orderId, OrderItemId orderItemId) throws OrderIdNotExistException, OrderItemIdNotExistException;

}
