package com.example.productcatalog.domain.repository;

import com.example.productcatalog.domain.models.MusicalInstrument;
import com.example.productcatalog.domain.models.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicalInstrumentRepository extends JpaRepository<MusicalInstrument, ProductId> {
}
