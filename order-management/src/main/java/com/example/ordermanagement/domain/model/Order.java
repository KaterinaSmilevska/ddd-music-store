package com.example.ordermanagement.domain.model;

import com.example.ordermanagement.domain.valueobjects.MusicalInstrument;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

@Entity
@Table(name = "orders")
@Getter
public class Order extends AbstractEntity<OrderId> {
    
    private Instant dateOrdered;

    @Enumerated(value = EnumType.STRING)
    private OrderState orderState;

    @Column(name="order_currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order() {
        super(OrderId.randomId(OrderId.class));
    }

    public Order(Instant now, Currency currency) {
        super(OrderId.randomId(OrderId.class));
        this.dateOrdered = now;
        this.currency = currency;
    }


    public Money total(){
        return orderItems
                .stream()
                .map(OrderItem::subtotal)
                .reduce(new Money(currency, 0), Money::add);
    }

    public OrderItem addItem(@NonNull MusicalInstrument product, int qty) {
        Objects.requireNonNull(product,"product must not be null");
        var item  = new OrderItem(product.getId(),product.getInstrumentPrice(),qty);
        orderItems.add(item);
        return item;
    }

    public void removeItem(@NonNull OrderItemId orderItemId) {
        Objects.requireNonNull(orderItemId,"Order Item must not be null");
        orderItems.removeIf(v->v.getId().equals(orderItemId));
    }
}
