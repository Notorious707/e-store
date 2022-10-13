package com.estore.paypalservice.service;

import com.estore.paypalservice.model.PaymentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class PaypalServiceImpl implements PaypalService{

    @Override
    public String makePayment(PaymentDTO paymentDTO) {
        if(paymentDTO.getAmount() == null || paymentDTO.getOrderId() == null){
            log.info("Payment is not made by Paypal service");
            return "Payment Unsuccessful by Paypal";
        }else
            log.info("Payment is made by Paypal service");
            return "Payment Successfully made by Paypal ";

    }
}
