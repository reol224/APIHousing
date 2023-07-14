package com.conestoga.APIHousing.controller;

import com.conestoga.APIHousing.model.NotificationRequest;
import com.conestoga.APIHousing.service.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final FirebaseService firebaseService;

    @Autowired
    public NotificationController(FirebaseService firebaseService) {
        this.firebaseService = firebaseService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createNotification(@RequestBody NotificationRequest request) {
        try {
            firebaseService.sendPushNotification(request.getTitle(), request.getDescription());
            return ResponseEntity.ok("Notification sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send notification: " + e.getMessage());
        }
    }
}
