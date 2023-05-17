package com.example.ordermanagement.domain.model;

import com.example.ordermanagement.domain.valueobjects.ProductId;
import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.base.DomainObjectId;
import com.example.sharedkernel.domain.financial.Money;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;

@Entity
@Table(name = "order_item")
@Getter
public class OrderItem extends AbstractEntity<OrderItemId> {

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="item_price_amount")),
            @AttributeOverride(name="currency", column = @Column(name="item_price_currency"))
    })
    @Column(nullable = false)
    private Money itemPrice;

    @Column(name = "qty", nullable = false)
    private int quantity;

    @AttributeOverride(name = "id", column = @Column(name = "product_id", nullable = false))
    private ProductId productId;

    public OrderItem(@NonNull ProductId productId, @NonNull Money instrumentPrice, int qty) {
        super(DomainObjectId.randomId(OrderItemId.class));
        this.productId = productId;
        this.itemPrice = instrumentPrice;
        this.quantity = qty;

    }

    public OrderItem() {
        super(DomainObjectId.randomId(OrderItemId.class));
    }

    public Money subtotal(){
        return itemPrice.multiply(quantity);
    }

}
