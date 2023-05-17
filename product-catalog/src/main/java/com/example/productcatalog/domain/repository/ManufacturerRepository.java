package com.example.productcatalog.domain.repository;

import com.example.productcatalog.domain.models.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
}
