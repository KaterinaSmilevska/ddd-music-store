package com.example.productcatalog.services;

import com.example.productcatalog.domain.models.MusicalInstrument;
import com.example.productcatalog.domain.models.ProductId;
import com.example.productcatalog.services.form.MusicalInstrumentForm;

import java.util.List;

public interface MusicalInstrumentService {

    MusicalInstrument findById(ProductId id);
    MusicalInstrument createProduct(MusicalInstrumentForm form);
    MusicalInstrument orderItemCreated(ProductId productId, int quantity);
    MusicalInstrument orderItemRemoved(ProductId productId, int quantity);
    List<MusicalInstrument> getAll();

}
