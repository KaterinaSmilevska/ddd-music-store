package com.example.productcatalog.services.form;

import com.example.productcatalog.domain.models.Category;
import com.example.productcatalog.domain.models.Manufacturer;
import com.example.sharedkernel.domain.financial.Money;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MusicalInstrumentForm {

    @NotNull
    private String instrumentName;

    @NotNull
    private Money price;

    @NotNull
    private boolean isAvailable;

    @NotNull
    private Manufacturer manufacturer;

    @NotNull
    private Category category;

    @NotNull
    private int sales;

}
