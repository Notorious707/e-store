package com.estore.shippingservice.repository;

import com.estore.shippingservice.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
    Shipping findByOrderId(Long id);
}

