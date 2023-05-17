package com.example.productcatalog.domain.models;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "category")
@Getter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, unique = true)
    private String categoryName;

    public Category(String categoryName){
        this.categoryName = categoryName;
    }

    public Category(){

    }

}
