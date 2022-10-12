package com.estore.shippingservice.controller;

import com.estore.shippingservice.entity.Shipping;
import com.estore.shippingservice.model.ShippingDTO;
import com.estore.shippingservice.model.State;
import com.estore.shippingservice.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/shipping")
public class ShippingController {
    @Autowired
    private ShippingService shippingService;

    @PostMapping("/")
    public String save(@RequestBody Shipping shipping){
        return shippingService.save(shipping);
    }
    @GetMapping("/get")
    public List<Shipping> get(){
        return shippingService.get();
    }
    @PutMapping("/{id}")
    public String update(@PathVariable Long id,
            @RequestBody ShippingDTO shippingDTO){
        shippingService.update(id,shippingDTO);
        return "Updated Successfully";
    }
}
