package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.dtos.ResetPasswordRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ResetPasswordController {
    @Autowired
    private PasswordEncoder passwordEncoder; // You need to configure this bean

    private Map<String, String> resetTokens = new ConcurrentHashMap<>();

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequestDTO request) {
        String email = request.getEmail();
        String resetToken = request.getResetToken();
        String newPassword = request.getNewPassword();

        // Verify if the reset token is valid and matches the email
        if (resetTokens.containsKey(email) && resetTokens.get(email).equals(resetToken)) {
            // Update the user's password with the new password (assuming you have a user database)
            // Implement the logic to update the user's password here, using the provided newPassword.
            // For example, you might have a UserService to handle user-related operations.

            // After successfully resetting the password, remove the reset token from the map (resetTokens).
            resetTokens.remove(email);

            return ResponseEntity.ok("Password reset successful.");
        }

        return ResponseEntity.badRequest().body("Invalid reset token or expired reset token.");
    }
}

