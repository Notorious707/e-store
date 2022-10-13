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
    private ProductRepository productRepository;
    @Override
    public List<Product> getList() {
        return productRepository.findAll();
    }
    @Override
    public void save(ProductDTO productDTO) {
        Product product = new Product(productDTO.getName(),productDTO.getVendor(),productDTO.getCategory(), productDTO.getPrice(),productDTO.getUnit(),productDTO.getQuantity());
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
        product.setUnit(productDTO.getUnit());
        product.setQuantity(productDTO.getQuantity());
        productRepository.save(product);

    }
}
