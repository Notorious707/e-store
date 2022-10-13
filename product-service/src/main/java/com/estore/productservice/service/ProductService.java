package com.estore.productservice.service;

import com.estore.productservice.entity.Product;
import com.estore.productservice.model.ProductDTO;

import java.util.List;

public interface ProductService {
    List<Product> getList();


    void save(ProductDTO productDTO);

    Product get(Long id);

    void delete(Long id);

    void update(Long id, ProductDTO productDTO);
}
