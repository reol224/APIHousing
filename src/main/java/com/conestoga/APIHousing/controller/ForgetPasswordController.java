package com.conestoga.APIHousing.controller;

import com.stripe.param.issuing.CardUpdateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ForgetPasswordController {
    private Map<String, String> resetTokens = new ConcurrentHashMap<>();

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/forget-password")
    public ResponseEntity<String> forgetPassword(@RequestBody Map<String, String> requestBody) {
        String email = requestBody.get("email");

        // Check if the user with the provided email exists in your user database
        // If the email does not exist, return an appropriate response indicating that the email is not registered.
        // Otherwise, proceed with the password reset process.

        // Generate a random reset token
        String resetToken = generateRandomResetToken();

        // Store the reset token in the map (temporary storage)
        resetTokens.put(email, resetToken);

        // Send the reset token to the user's email address
        sendResetTokenToEmail(email, resetToken);

        return ResponseEntity.ok("Reset token sent to your email address.");
    }

    private String generateRandomResetToken() {
        // Generate a random reset token (e.g., a 6-digit code)
        Random random = new Random();
        int token = 100_000 + random.nextInt(900_000);
        return String.valueOf(token);
    }

    private void sendResetTokenToEmail(String email, String resetToken) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(email);
            helper.setSubject("Password Reset Token");
            helper.setText("Your password reset token is: " + resetToken);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle the exception or log the error appropriately
        }
    }
}

