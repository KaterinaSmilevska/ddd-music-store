package com.example.ordermanagement.domain.valueobjects;

import com.example.sharedkernel.domain.base.DomainObjectId;
import jakarta.persistence.Embeddable;

@Embeddable
public class ProductId extends DomainObjectId {

    public ProductId() {
        super(ProductId.randomId(ProductId.class).getId());
    }

    public ProductId(String uuid) {
        super(uuid);
    }
}
