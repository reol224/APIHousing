package com.conestoga.APIHousing.service;

import com.stripe.Stripe;
import com.stripe.net.RequestOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class StripeService {

    @Value("${Stripe.apiKey}")
    private String stripeKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeKey;
        RequestOptions requestOptions = RequestOptions.builder()
                .setApiKey(stripeKey)
                .build();
    }
}
