package com.estore.shippingservice.service;

import com.estore.shippingservice.entity.Shipping;
import com.estore.shippingservice.model.ShippingDTO;

import java.util.List;

public interface ShippingService {
    String save(Shipping shipping);

    List<Shipping> get();

    void update(Long id,ShippingDTO shippingDTO);
}
