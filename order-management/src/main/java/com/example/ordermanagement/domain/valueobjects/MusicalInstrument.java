package com.example.ordermanagement.domain.valueobjects;

import com.example.sharedkernel.domain.base.ValueObject;
import com.example.sharedkernel.domain.financial.Currency;
import com.example.sharedkernel.domain.financial.Money;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MusicalInstrument implements ValueObject {

    private final ProductId id;
    private final String instrumentName;
    private final Money instrumentPrice;
    private final int sales;

    private MusicalInstrument() {
        this.id = ProductId.randomId(ProductId.class);
        this.instrumentName = "";
        this.instrumentPrice = Money.valueOf(Currency.MKD, 0);
        this.sales = 0;

    }

    @JsonCreator
    public MusicalInstrument(@JsonProperty("id") ProductId id,
                             @JsonProperty("instrumentName") String instrumentName,
                             @JsonProperty("instrumentPrice") Money instrumentPrice,
                             @JsonProperty("sales") int sales){
        this.id = id;
        this.instrumentName = instrumentName;
        this.instrumentPrice = instrumentPrice;
        this.sales = sales;
    }
}
