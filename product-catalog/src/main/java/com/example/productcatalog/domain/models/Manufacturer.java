package com.example.productcatalog.domain.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "manufacturer")
@Getter
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manufacturerId;

    @Column(nullable = false, unique = true)
    private String manufacturerName;

    @Column(nullable = false, unique = true)
    private String manufacturerCountry;

    public Manufacturer(String manufacturerName, String manufacturerCountry){
        this.manufacturerName = manufacturerName;
        this.manufacturerCountry = manufacturerCountry;
    }

    public Manufacturer(){

    }
}
