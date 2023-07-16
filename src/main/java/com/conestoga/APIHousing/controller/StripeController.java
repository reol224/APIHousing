package com.conestoga.APIHousing.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.param.CustomerCreateParams;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stripe")
public class StripeController {

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
        } catch (StripeException ex) {
            throw new RuntimeException(ex);
        }
        System.out.println(customer.getName());
        System.out.println(customer.getPhone());
        System.out.println(customer.getAddress());
        System.out.println(customer.getDescription());
        System.out.println(customer.getEmail());
    }
}
