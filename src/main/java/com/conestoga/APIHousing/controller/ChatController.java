package com.conestoga.APIHousing.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conestoga.APIHousing.model.ChatMessage;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @PostMapping("/messages")
    public void sendMessage(@RequestBody ChatMessage message) {
        // Process and handle the chat message
        // You can store it in the database, broadcast it to connected clients, etc.
    }

    @GetMapping("/messages")
    public List<ChatMessage> getChatHistory() {
        // Retrieve and return the chat history
        // You can fetch messages from the database or any other data source
        // and return them as a list of ChatMessage objects
        return null;
    }

    // Other chat-related endpoints
}
