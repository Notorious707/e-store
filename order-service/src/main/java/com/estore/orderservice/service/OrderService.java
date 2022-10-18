package com.estore.orderservice.service;

import com.estore.orderservice.exception.NotFoundException;
import com.estore.orderservice.model.*;
import com.estore.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import  com.estore.orderservice.model.OrderResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
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
    @Autowired
    private TokenService tokenService;


    @Override
    public Order findById(Long id, String authHeader,HttpServletResponse httpServletResponse) throws IOException {
        ValidateDTO  validateDTO = tokenService.validateToken(authHeader);
        if (validateDTO == null || !validateDTO.isSuccess()) {httpServletResponse.sendError(401,"UNAUTHORIZED");};
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
    public List<Order> findAll(String authHeader, HttpServletResponse response) throws IOException {
        ValidateDTO validateDTO = tokenService.validateToken(authHeader);
        if (validateDTO == null || !validateDTO.isSuccess()){ response.sendError(401,"UNAUTHORIZED"); }

        List<Order> orders=orderRepository.findAll();
        orders.stream().map(order->{
            return mapProducts(order);
        }).collect(Collectors.toList());
        return orderRepository.findAll();
    }

    @Override
    public ResponseEntity<OrderResponse> update(Long id, Order order, String authHeader) {
        ValidateDTO validateDTO = tokenService.validateToken(authHeader);
        if (validateDTO == null || !validateDTO.isSuccess()) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Order foundOrder=orderRepository.findById(id).get();
        foundOrder.setShippingAddress(order.getShippingAddress());
        foundOrder.setPaymentType(order.getPaymentType());
        orderRepository.save(foundOrder);
        OrderResponse response = new OrderResponse("Order Updated","Ok");
         return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrderResponse> create(OrderRequest orderRequest, String authHeader) {
        ValidateDTO validateDTO = tokenService.validateToken(authHeader);
        if (validateDTO == null || !validateDTO.isSuccess()) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Order order = new Order(orderRequest.getAccountId(),orderRequest.getItems(),orderRequest.getShippingAddress(),orderRequest.getStatus(),orderRequest.getPaymentType(),orderRequest.getPaymentId(),orderRequest.getPayment(),orderRequest.getAmount());
        orderRequest.setStatus(OrderState.CREATED);
        orderRepository.save(order);
//        order.setStatus(OrderState.SHIPPED);
        OrderResponse orderResponse = new OrderResponse("Order is created!","CREATED");
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);


    }

    @Override
    public ResponseEntity<OrderResponse> setPaymentId(Long id, OrderRequest orderRequest, String authHeader) {
        ValidateDTO validateDTO = tokenService.validateToken(authHeader);
        if (validateDTO == null || !validateDTO.isSuccess()) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        Order order = orderRepository.findById(id).get();
        order.setPaymentId(orderRequest.getPaymentId());
        orderRepository.save(order);
        PaymentResponse paymentResponse = new PaymentResponse();
        OrderResponse response = new OrderResponse("Updated", paymentResponse.getStatus());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
