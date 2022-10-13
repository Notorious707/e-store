package com.estore.orderservice.service;

import com.estore.orderservice.exception.NotFoundException;
import com.estore.orderservice.model.Order;
import com.estore.orderservice.model.OrderState;
import com.estore.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(()->new NotFoundException("Order not found with this ID!"));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order update(Long id, Order order) {
        Order foundOrder=this.findById(id);
        foundOrder.setShippingAddress(order.getShippingAddress());
        foundOrder.setPaymentType(order.getPaymentType());
        return orderRepository.save(foundOrder);
    }

    @Override
    public Order create(Order order) {
        order.setStatus(OrderState.CREATED);
        return orderRepository.save(order);
    }
}
