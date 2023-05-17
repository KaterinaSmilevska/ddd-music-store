package com.example.productcatalog.domain.models;

import com.example.sharedkernel.domain.base.AbstractEntity;
import com.example.sharedkernel.domain.financial.Money;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name="musical_instrument")
@Getter
public class MusicalInstrument extends AbstractEntity<ProductId> {

    @Column(nullable = false, unique = true)
    private String instrumentName;

    private int sales = 0;

    @AttributeOverrides({
            @AttributeOverride(name="amount", column = @Column(name="price_amount")),
            @AttributeOverride(name="currency", column = @Column(name="price_currency"))
    })
    @Column(nullable = false)
    private Money instrumentPrice;

    @ManyToOne
    private Manufacturer manufacturer;

    @ManyToOne
    private Category category;

    @Column(nullable = false)
    private boolean isAvailable;

    public MusicalInstrument(){
        super(ProductId.randomId(ProductId.class));
    }

    public static MusicalInstrument build(String instrumentName, Money instrumentPrice,
                                          int sales, Manufacturer manufacturer, Category category,
                                          boolean isAvailable){

        MusicalInstrument musicalInstrument = new MusicalInstrument();
        musicalInstrument.instrumentName = instrumentName;
        musicalInstrument.instrumentPrice = instrumentPrice;
        musicalInstrument.sales = sales;
        musicalInstrument.manufacturer = manufacturer;
        musicalInstrument.category = category;
        musicalInstrument.isAvailable = isAvailable;
        return  musicalInstrument;

    }


    public void addSales(int qty) {
        this.sales = this.sales - qty;
    }

    public void removeSales(int qty) {
        this.sales -= qty;
    }


    public void makeAvailable(){
        this.isAvailable = true;
    }

    public void makeUnavailable(){
        this.isAvailable = false;
    }

}
