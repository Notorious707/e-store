package com.estore.orderservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String vendor;
    private String category;
    private Double price;
    private String unit;
    private Long quantity;

    public Product(String name, String vendor, String category, Double price, String unit, Long quantity) {
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
        this.unit = unit;
        this.quantity = quantity;
    }
}
