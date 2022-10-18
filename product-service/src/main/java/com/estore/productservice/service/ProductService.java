package com.estore.productservice.service;

import com.estore.productservice.entity.Product;
import com.estore.productservice.model.ProductDTO;
import com.estore.productservice.model.ProductResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getList(String authHeader,HttpServletResponse httpServletResponse) throws IOException;


    ProductResponse save(ProductDTO productDTO, String authHeader, HttpServletResponse httpServletResponse) throws IOException;

    Product get(Long id,String authHeader, HttpServletResponse response) throws IOException;

    String delete(Long id,String authHeader, HttpServletResponse response) throws IOException;

    String update(Long id, ProductDTO productDTO, String authHeader,HttpServletResponse response) throws IOException;
}
