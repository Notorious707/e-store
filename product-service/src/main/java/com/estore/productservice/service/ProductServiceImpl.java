package com.estore.productservice.service;

import com.estore.productservice.entity.Product;
import com.estore.productservice.model.ProductDTO;
import com.estore.productservice.model.ProductResponse;
import com.estore.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private TokenService tokenService;
    @Override
    public List<Product> getList(String authHeader, HttpServletResponse httpServletResponse) throws IOException {
        ProductResponse productResponse = tokenService.validateToken(authHeader);
        if (productResponse == null || !productResponse.isSuccess()) { httpServletResponse.sendError(401,"UNAUTHORIZED");}

        return productRepository.findAll();
    }
    @Override
    public ProductResponse save(ProductDTO productDTO, String authHeader, HttpServletResponse response) throws IOException {
        ProductResponse productResponse = tokenService.validateToken(authHeader);
        if (productResponse == null || !productResponse.isSuccess()){
            response.sendError(401,"UNAUTHORIZED");
        }
        Product product = new Product(productDTO.getName(),productDTO.getVendor(),productDTO.getCategory(), productDTO.getPrice(),productDTO.getUnit(),productDTO.getQuantity());
        productRepository.save(product);
        ProductResponse productSavedResponse = new ProductResponse(true,"SAVED");
        return productSavedResponse;
    }

    @Override
    public Product get(Long id, String authHeader, HttpServletResponse response) throws IOException{
        ProductResponse productResponse = tokenService.validateToken(authHeader);
        if (productResponse == null || !productResponse.isSuccess()){
            response.sendError(401,"UNAUTHORIZED");
        }
        return productRepository.findById(id).get();
    }

    @Override
    public String delete(Long id,String authHeader,HttpServletResponse response) throws IOException {
        ProductResponse productResponse = tokenService.validateToken(authHeader);
        if (productResponse == null || !productResponse.isSuccess()){
            response.sendError(401,"UNAUTHORIZED");
        }
        productRepository.deleteById(id);
        return "DELETED";
    }

    @Override
    public String update(Long id, ProductDTO productDTO,String authHeader,HttpServletResponse response) throws IOException {
        ProductResponse productResponse = tokenService.validateToken(authHeader);
        if (productResponse == null || !productResponse.isSuccess()){
            response.sendError(401,"UNAUTHORIZED");
        }

        Product product = productRepository.findById(id).get();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setVendor(productDTO.getVendor());
        product.setCategory(productDTO.getCategory());
        product.setUnit(productDTO.getUnit());
        product.setQuantity(productDTO.getQuantity());
        productRepository.save(product);

        return "UPDATED";

    }
}
