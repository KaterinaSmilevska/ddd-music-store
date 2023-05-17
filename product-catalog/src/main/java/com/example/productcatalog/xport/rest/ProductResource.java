package com.example.productcatalog.xport.rest;

import com.example.productcatalog.domain.models.MusicalInstrument;
import com.example.productcatalog.services.MusicalInstrumentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductResource {

    private final MusicalInstrumentService musicalInstrumentService;

    @GetMapping
    public List<MusicalInstrument> getAll() {
        return musicalInstrumentService.getAll();
    }

}
