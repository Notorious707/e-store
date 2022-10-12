package com.estore.productservice.controller;

import com.estore.productservice.entity.Product;
import com.estore.productservice.model.ProductDTO;
import com.estore.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductControlller {
    @Autowired
    ProductService productService;
    @PostMapping("/save")
    public String save(@RequestBody Product product){
        productService.save(product);
        return "Product is saved!";
    }
    @GetMapping("/{id}")
    public Product get(@PathVariable Long id){
        return productService.get(id);
    }

    @GetMapping("/list")
    public List<Product> getList(){
        return productService.getList();
    }
    @DeleteMapping ("/delete/{id}")
    public String delete(@PathVariable Long id){
         productService.delete(id);
         return "Product deleted!";
    }
    @PutMapping ("/update/{id}")
    public String update(@PathVariable Long id, @RequestBody ProductDTO productDTO){
       productService.update(id,productDTO);
       return "Product updated!";
    }

}
