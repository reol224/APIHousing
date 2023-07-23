package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.dtos.PaymentRequestDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.Invoice;
import com.stripe.model.PaymentIntent;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.InvoiceCreateParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/payments")
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
            logger.info(customer.getId());
            logger.info(customer.getName());
            logger.info(customer.getPhone());
            logger.info(customer.getAddress().getCity());
            logger.info(customer.getDescription());
            logger.info(customer.getEmail());
        } catch (StripeException ex) {
            logger.warning(ex.getMessage());
        }
    }

    @PostMapping("/pay")
    public ResponseEntity<String> createPayment(@RequestBody PaymentRequestDTO paymentRequest) {
        try {
            // Create a PaymentIntent with the payment request data
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(paymentRequest.getAmount())
                    .setCurrency(paymentRequest.getCurrency())
                    .setPaymentMethod("pm_card_visa")
                    .setCustomer(paymentRequest.getStripeCustomerId())
                    // Add any additional parameters, such as metadata or description, if needed
                    .build();


            PaymentIntent paymentIntent = PaymentIntent.create(params);
            paymentIntent.confirm();


// Retrieve the Invoice associated with the PaymentIntent
            Invoice invoice = Invoice.create(InvoiceCreateParams.builder()
                    .setCustomer(paymentRequest.getStripeCustomerId())
                    .setAutoAdvance(true)
                    .build());
            //Invoice invoice = Invoice.retrieve(paymentIntent.getInvoice());
            if (invoice == null) {
                // If the invoice is null, log an error and return an appropriate response
                logger.severe("No associated Invoice found for PaymentIntent: " + paymentIntent.getId());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No associated Invoice found for PaymentIntent.");
            } else {
                // Print Invoice details
                logger.info("Invoice ID: " + invoice.getId());
                logger.info("Customer ID: " + invoice.getCustomer());
                logger.info("Customer Email: " + invoice.getCustomerEmail());
                logger.info("Customer Name: " + invoice.getCustomerName());
                logger.info("Customer Phone: " + invoice.getCustomerPhone());
                logger.info("Customer Address: " + invoice.getCustomerAddress().getCity() + ", " + invoice.getCustomerAddress().getCountry());
                logger.info("Amount Paid: " + invoice.getAmountPaid());
            }


            // Return the client secret of the PaymentIntent
            logger.info("Successfully created payment intent: " + paymentIntent.getId());
            return ResponseEntity.ok(paymentIntent.getInvoice());
        } catch (StripeException e) {
            // Handle any exceptions that might occur during the Stripe API call
            // Log the error or notify the user
            logger.severe("Error creating payment intent: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
