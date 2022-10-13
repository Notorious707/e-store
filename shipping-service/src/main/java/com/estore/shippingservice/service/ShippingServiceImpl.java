package com.estore.shippingservice.service;

import com.estore.shippingservice.entity.Order;
import com.estore.shippingservice.entity.Shipping;
import com.estore.shippingservice.model.ShippingDTO;
import com.estore.shippingservice.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    ShippingRepository shippingRepository;

    @Autowired
    RestTemplate orderServiceApi;
    @Override
    public String save(Shipping shipping) {
        if(shippingRepository.existsById(shipping.getOrderId())){
            return "Same order id";
        }else
        shippingRepository.save(shipping);
        return "Successful";
    }
    @Override
    public List<Shipping> get() {
        List<Shipping> shippings=shippingRepository.findAll();
        shippings.stream().map(shipping->{
            Order order=orderServiceApi.getForObject("/orders/"+shipping.getOrderId(),Order.class);
            shipping.setOrder(order);
            return shipping;
        }).collect(Collectors.toList());
        return shippings;
    }

    @Override
    public void update(Long id, ShippingDTO shippingDTO) {
        Shipping shipping = shippingRepository.findByOrderId(id);
        shipping.setCourier(shippingDTO.getCourier());
        shipping.setState(shippingDTO.getState());
        shippingRepository.save(shipping);
    }
}
