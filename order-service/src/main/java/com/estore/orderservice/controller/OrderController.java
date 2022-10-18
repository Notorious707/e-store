package com.estore.orderservice.controller;

import com.estore.orderservice.model.Order;
import com.estore.orderservice.model.OrderRequest;
import com.estore.orderservice.model.OrderResponse;
import com.estore.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> findAll(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, HttpServletResponse httpResponse) throws IOException {

        return orderService.findAll(authorization,httpResponse) ;
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable("id") Long id, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, HttpServletResponse httpServletResponse) throws IOException{
        return orderService.findById(id,authorization, httpServletResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderResponse> update(@PathVariable("id") Long id,@RequestBody Order order, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        return orderService.update(id,order,authorization);
    };

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest orderRequest, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        return orderService.create(orderRequest,authorization);
    }
    @PostMapping("/set-payment-id/{id}/{paymentId}")
    public ResponseEntity<OrderResponse> setPaymentId(@PathVariable("id") Long id, @PathVariable("paymentId") Long paymentId,@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        return orderService.setPaymentId(id,paymentId,authorization);
    }
}
