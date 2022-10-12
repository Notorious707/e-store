package com.estore.shippingservice.service;

import com.estore.shippingservice.entity.Shipping;
import com.estore.shippingservice.model.ShippingDTO;
import com.estore.shippingservice.repository.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShippingServiceImpl implements ShippingService {

    @Autowired
    ShippingRepository shippingRepository;
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
        return shippingRepository.findAll();
    }

    @Override
    public void update(Long id, ShippingDTO shippingDTO) {
        Shipping shipping = shippingRepository.findByOrderId(id);
        shipping.setCourier(shippingDTO.getCourier());
        shipping.setState(shippingDTO.getState());
        shippingRepository.save(shipping);
    }
}
