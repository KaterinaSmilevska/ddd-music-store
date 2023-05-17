package com.example.productcatalog.domain.models;

import com.example.sharedkernel.domain.base.DomainObjectId;
import lombok.NonNull;

public class ProductId extends DomainObjectId {

    private ProductId() {
        super(ProductId.randomId(ProductId.class).getId());
    }

    public ProductId(@NonNull String uuid) {
        super(uuid);
    }

    public static ProductId of(String uuid) {
        ProductId p = new ProductId(uuid);
        return p;
    }

}
