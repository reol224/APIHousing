package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.configs.chat.ChatWebSocketHandler;
import com.conestoga.APIHousing.interfaces.ChatMessageRepository;
import com.conestoga.APIHousing.model.ChatMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class ChatMessageService {
    private final ChatMessageRepository chatMessageRepository;

@Autowired
public ChatMessageService(ChatMessageRepository chatMessageRepository) {
    this.chatMessageRepository = chatMessageRepository;
}

    public List<ChatMessage> getAllChatMessages() {
        return chatMessageRepository.findAll();
    }

   public ChatMessage saveChatMessage(ChatMessage chatMessage) {
    ChatMessage savedChatMessage = chatMessageRepository.save(chatMessage);
    ChatWebSocketHandler.getInstance().broadcastMessage(savedChatMessage); // Broadcast the message
    return savedChatMessage;
}

}
