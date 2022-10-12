package com.estore.shippingservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDTO {
    private Long orderId;
    private String courier;
    private State state;
}
