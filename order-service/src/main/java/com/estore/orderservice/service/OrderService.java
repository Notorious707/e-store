package com.estore.orderservice.service;

import com.estore.orderservice.exception.NotFoundException;
import com.estore.orderservice.model.*;
import com.estore.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate productServiceApi;
    @Autowired
    private RestTemplate paymentServiceApi;

    @Override
    public Order findById(Long id) {
        Order order=orderRepository.findById(id).orElseThrow(()->new NotFoundException("Order not found with this ID!"));
        return mapProducts(order);
    }

    private Order mapProducts(Order order) {
        AtomicReference<Double> amount= new AtomicReference<>(0.0);
        if(order.getPaymentId()!=null){
            Payment payment=paymentServiceApi.getForObject("/payment/getpayment/"+order.getId(), Payment.class);
            order.setPayment(payment);
        }
        List<OrderItem> orderItems=order.getItems();
        orderItems.stream().map(i->{
            Product product=productServiceApi.getForObject("/product/"+i.getProductId(), Product.class);
            amount.updateAndGet(v -> v + product.getPrice() * i.getQuantity());
            order.setAmount(amount.get());
            i.setProduct(product);
            return i;
        }).collect(Collectors.toList());
        return order;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders=orderRepository.findAll();
        orders.stream().map(order->{
            return mapProducts(order);
        }).collect(Collectors.toList());
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
