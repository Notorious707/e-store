package com.estore.shippingservice.entity;

import com.estore.shippingservice.model.State;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
public class Shipping {
    @Id
    @GeneratedValue
    private Long id;
    private Long orderId;
    private String courier;
    private State state;

    public Shipping(Long orderId, String courier, State state) {
        this.orderId = orderId;
        this.courier = courier;
        this.state = state;
    }
}
