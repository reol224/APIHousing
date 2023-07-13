package com.conestoga.APIHousing.service;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FirebaseService {

    @PostConstruct
    public void initializeFirebase() throws IOException {
        InputStream serviceAccount = new ClassPathResource("firebase/serviceAccountKey.json").getInputStream();

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        FirebaseApp.initializeApp(options);
    }

    public void sendPushNotification(String title, String description) {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(description)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setTopic("topic") // Replace with your topic or token
                .build();

        try {
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Successfully sent message: " + response);
        } catch (Exception e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }
}
