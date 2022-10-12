package com.estore.bankaccountservice.service;

import com.estore.bankaccountservice.model.BankAccountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class BankAccountServiceImpl implements BankAccountService{
    @Override
    public String makePayment(BankAccountDTO bankAccountDTO) {
        if(bankAccountDTO.getAmount() == null || bankAccountDTO.getOrderId() == null){
            log.info("Payment made by Paypal service");
            return "Payment Unsuccessful by Bank Account";
        }else
            log.info("Payment can be made by Paypal service");
        return "Payment Successfully made by Bank Account";
    }

}
