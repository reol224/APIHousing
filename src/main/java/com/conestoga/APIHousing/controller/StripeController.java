package com.conestoga.APIHousing.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {

    Logger logger = Logger.getLogger(StripeController.class.getName());

    @GetMapping("/test")
    public void stripe() {
        CustomerCreateParams params =
                CustomerCreateParams
                        .builder()
                        .putMetadata("order_id", "6735")
                        .setName("Jenny Rosen")
                        .setPhone("4158675309")
                        .setAddress(
                                CustomerCreateParams.Address.builder()
                                        .setLine1("510 Townsend St")
                                        .setPostalCode("98140")
                                        .setCity("San Francisco")
                                        .setState("CA")
                                        .setCountry("US")
                                        .build())
                        .setDescription("Example description")
                        .setEmail("test@example.com")
                        .setPaymentMethod("pm_card_visa")  // obtained via Stripe.js
                        .build();
        Customer customer = null;
        try {
            customer = Customer.create(params);
            logger.info(customer.getName());
            logger.info(customer.getPhone());
            logger.info(customer.getAddress().getCity());
            logger.info(customer.getDescription());
            logger.info(customer.getEmail());
        } catch (StripeException ex) {
            logger.warning(ex.getMessage());
        }
    }
}
