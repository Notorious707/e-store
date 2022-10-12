package com.estore.productservice.service;

import com.estore.productservice.entity.Product;
import com.estore.productservice.model.ProductDTO;
import com.estore.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getList() {
        return productRepository.findAll();
    }
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product get(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void update(Long id, ProductDTO productDTO) {
        Product product = productRepository.findById(id).get();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setVendor(productDTO.getVendor());
        product.setCategory(productDTO.getCategory());
        productRepository.save(product);

    }
}
