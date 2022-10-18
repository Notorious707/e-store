package com.estore.orderservice.service;

import com.estore.orderservice.model.Order;
import com.estore.orderservice.model.OrderRequest;
import com.estore.orderservice.model.OrderResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

public interface IOrderService {
    Order findById(Long id, String authorization, HttpServletResponse httpServletResponse) throws IOException ;

    List<Order> findAll(String authHeader,HttpServletResponse httpResponse) throws IOException ;

    ResponseEntity<OrderResponse> create(OrderRequest orderRequest, String authHeader);

    ResponseEntity<OrderResponse> setPaymentId(Long id, OrderRequest orderRequest, String authorization);

    ResponseEntity<OrderResponse> update(Long id, Order order, String authorization);
}
