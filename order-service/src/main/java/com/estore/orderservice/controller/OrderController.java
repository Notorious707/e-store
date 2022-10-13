package com.estore.orderservice.controller;

import com.estore.orderservice.model.Order;
import com.estore.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> findAll(){
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") Long id){
        return orderService.findById(id);
    }

    @PutMapping("/{id}")
    public Order update(@PathVariable("id") Long id,@RequestBody Order order){
        return orderService.update(id,order);
    };

    @PostMapping
    public Order create(@RequestBody Order order){
        return orderService.create(order);
    }
    @PostMapping("/set-payment-id/{id}/{paymentId}")
    public Order setPaymentId(@PathVariable("id") Long id, @PathVariable("paymentId") Long paymentId){
        Order order=orderService.findById(id);
        order.setPaymentId(paymentId);
        return orderService.create(order);
    }
}
