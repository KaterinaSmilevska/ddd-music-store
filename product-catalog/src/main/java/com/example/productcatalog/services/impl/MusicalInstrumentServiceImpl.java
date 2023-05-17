package com.example.productcatalog.services.impl;

import com.example.productcatalog.domain.exceptions.InstrumentNotFoundException;
import com.example.productcatalog.domain.models.MusicalInstrument;
import com.example.productcatalog.domain.models.ProductId;
import com.example.productcatalog.domain.repository.MusicalInstrumentRepository;
import com.example.productcatalog.services.MusicalInstrumentService;
import com.example.productcatalog.services.form.MusicalInstrumentForm;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class MusicalInstrumentServiceImpl implements MusicalInstrumentService {

    private final MusicalInstrumentRepository musicalInstrumentRepository;

    @Override
    public MusicalInstrument findById(ProductId id) {
        return this.musicalInstrumentRepository.findById(id)
                .orElseThrow(InstrumentNotFoundException::new);
    }

    @Override
    public MusicalInstrument createProduct(MusicalInstrumentForm form) {
        MusicalInstrument p = MusicalInstrument
                .build(form.getInstrumentName(),form.getPrice(),form.getSales(),
                        form.getManufacturer(), form.getCategory(), form.isAvailable());
        musicalInstrumentRepository.save(p);
        return p;

    }

    @Override
    public MusicalInstrument orderItemCreated(ProductId productId, int quantity) {
        MusicalInstrument p = musicalInstrumentRepository
                .findById(productId).orElseThrow(InstrumentNotFoundException::new);
        p.addSales(quantity);
        musicalInstrumentRepository.saveAndFlush(p);
        return p;

    }

    @Override
    public MusicalInstrument orderItemRemoved(ProductId productId, int quantity) {
        MusicalInstrument p = musicalInstrumentRepository
                .findById(productId).orElseThrow(InstrumentNotFoundException::new);
        p.removeSales(quantity);
        musicalInstrumentRepository.saveAndFlush(p);
        return p;

    }

    @Override
    public List<MusicalInstrument> getAll() {
        return this.musicalInstrumentRepository.findAll();
    }
}
