package com.conestoga.APIHousing.configs.chat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ChatWebSocketHandler extends TextWebSocketHandler {
    private List<WebSocketSession> sessions = new ArrayList<>();


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Handle a new WebSocket connection
        System.out.println("WebSocket connection established: " + session.getId());
        sessions.add(session);

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle an incoming text message
        System.out.println("Received message from WebSocket: " + message.getPayload());
        String receivedMessage = message.getPayload();

        // Process the message and send a response
            for (WebSocketSession activeSession : sessions) {
            if (!activeSession.getId().equals(session.getId())) {
                activeSession.sendMessage(new TextMessage(receivedMessage));
            }     // 
          }  session.sendMessage(new TextMessage(receivedMessage));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Handle WebSocket connection closed
        System.out.println("WebSocket connection closed: " + session.getId() + ", Status: " + status.getCode());
        sessions.remove(session);

    }
}
