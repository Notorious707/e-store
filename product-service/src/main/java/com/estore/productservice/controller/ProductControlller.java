package com.estore.productservice.controller;

import com.estore.productservice.entity.Product;
import com.estore.productservice.model.ProductDTO;
import com.estore.productservice.model.ProductResponse;
import com.estore.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductControlller {
    @Autowired
    ProductService productService;
    @PostMapping("/save")
    public ProductResponse save(@RequestBody ProductDTO productDTO, @RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, HttpServletResponse httpServletResponse) throws IOException {
        return productService.save(productDTO,authHeader,httpServletResponse);

    }
    @GetMapping("/{id}")
    public Product get(@PathVariable Long id, @RequestHeader (HttpHeaders.AUTHORIZATION) String authHeader,HttpServletResponse response )throws IOException{
        return productService.get(id,authHeader,response);
    }

    @GetMapping("/list")
    public List<Product> getList(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader, HttpServletResponse httpServletResponse) throws IOException{
        return productService.getList(authHeader,httpServletResponse);
    }
    @DeleteMapping ("/delete/{id}")
    public String delete(@PathVariable Long id,@RequestHeader (HttpHeaders.AUTHORIZATION) String authHeader,HttpServletResponse response)  throws IOException{
        return productService.delete(id,authHeader,response);

    }
    @PutMapping ("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody ProductDTO productDTO,@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,HttpServletResponse response) throws IOException{
       return productService.update(id,productDTO,authHeader,response);
    }

}
