package com.conestoga.APIHousing.configs.chat;

import com.conestoga.APIHousing.model.ChatMessage;
import com.conestoga.APIHousing.service.ChatMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;



public class ChatWebSocketHandler extends TextWebSocketHandler {
    private List<WebSocketSession> sessions = new ArrayList<>();
     private final ChatMessageService chatMessageService;

    @Autowired
    public ChatWebSocketHandler(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    //get all sessions
    private List<WebSocketSession> getSessions() {
        return sessions;
    }



    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Handle a new WebSocket connection
        System.out.println("WebSocket connection established: " + session.getId());
        sessions.add(session);

    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
         String payload = message.getPayload();
    System.out.println("Received message: " + payload);

    // Convert the JSON payload to a ChatMessage object
    ObjectMapper objectMapper = new ObjectMapper();
    ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);

    // Save the chat message using the ChatMessageService
    chatMessageService.saveChatMessage(chatMessage);

       
        // Save the chat message using the ChatMessageService
        chatMessageService.saveChatMessage(chatMessage);

        // Broadcast the message to other connected clients if needed
        // Example:
        broadcastMessage(chatMessage);
    }

    private void broadcastMessage(ChatMessage chatMessage) {
        // Iterate over connected WebSocket sessions and send the message to each
        // session
        // Example:
        for (WebSocketSession session : getSessions()) {
            if (session.isOpen()) {
                try {
                    session.sendMessage(new TextMessage(chatMessage.getContent()));
                } catch (Exception e) {
                    // Handle exception if message sending fails
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // Handle WebSocket connection closed
        System.out.println("WebSocket connection closed: " + session.getId() + ", Status: " + status.getCode());
        sessions.remove(session);

    }
}
