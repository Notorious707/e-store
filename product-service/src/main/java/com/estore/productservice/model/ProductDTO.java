package com.estore.productservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String name;
    private String vendor;
    private String category;
    private Double price;
    private String unit;
    private Long quantity;
}
