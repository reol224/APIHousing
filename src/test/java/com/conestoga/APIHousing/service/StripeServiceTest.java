package com.conestoga.APIHousing.service;

import com.stripe.Stripe;
import com.stripe.net.RequestOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = "Stripe.apiKey=<YOUR_STRIPE_KEY_HERE>")
class StripeServiceTest {

    @Autowired
    private StripeService stripeService;

    @Value("${Stripe.apiKey}")
    private String stripeKey;

    @BeforeEach
    void setUp() {
        stripeService.init();
    }

    @Test
    void testInit() {
        // Ensure that the Stripe.apiKey is set correctly
        assertEquals(stripeKey, Stripe.apiKey);

        // Ensure that the RequestOptions is set correctly
        RequestOptions requestOptions = RequestOptions.getDefault();
        assertEquals(stripeKey, requestOptions.getApiKey());
    }
}
