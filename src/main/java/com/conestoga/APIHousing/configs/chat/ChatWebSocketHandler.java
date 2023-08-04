package com.conestoga.APIHousing.configs.chat;

import com.conestoga.APIHousing.model.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final List<WebSocketSession> sessions = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(ChatWebSocketHandler.class);

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // Handle a new WebSocket connection
        logger.info("WebSocket connection established: {}", session.getId());
        sessions.add(session);

        // Print number of sessions on the server
        logger.info("Number of sessions: {}", sessions.size());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        logger.info("Received message: {}", message.getPayload());
        ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);
        broadcastMessage(chatMessage);
        session.sendMessage(message);
    }

    public void broadcastMessage(ChatMessage chatMessage) {
        TextMessage message;
        try {
            message = new TextMessage(objectMapper.writeValueAsString(chatMessage));
        } catch (IOException e) {
            logger.error("Failed to convert chat message to JSON: {}", e.getMessage());
            return;
        }

        for (WebSocketSession session : getSessions()) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(message);
                } catch (IOException e) {
                    logger.error("Exception occurred while sending message to WebSocket client: {}", e.getMessage());
                    // Handle exception if message sending fails
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Handle WebSocket connection closed
        logger.info("WebSocket connection closed: {}, Status: {}", session.getId(), status.getCode());
        sessions.remove(session);
        logger.info("Number of sessions: {}", sessions.size());
    }

    // Encapsulate the list of WebSocket sessions
    private List<WebSocketSession> getSessions() {
        return sessions;
    }
}
