package com.estore.orderservice.service;

import com.estore.orderservice.model.Order;

import java.util.List;

public interface IOrderService {
    Order findById(Long id);
    List<Order> findAll();
    Order update(Long id,Order order);
    Order create(Order order);
}
