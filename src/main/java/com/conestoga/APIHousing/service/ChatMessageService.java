package com.conestoga.APIHousing.service;

import com.conestoga.APIHousing.configs.chat.ChatWebSocketHandler;
import com.conestoga.APIHousing.interfaces.ChatMessageRepository;
import com.conestoga.APIHousing.model.ChatMessage;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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


      public List<ChatMessage> getChatMessages(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").descending());
        Page<ChatMessage> chatMessagePage = chatMessageRepository.findAll(pageable);
        return chatMessagePage.getContent();
    }

   public ChatMessage saveChatMessage(ChatMessage chatMessage) {
    ChatMessage savedChatMessage = chatMessageRepository.save(chatMessage);
    ChatWebSocketHandler.getInstance().broadcastMessage(savedChatMessage); // Broadcast the message
    return savedChatMessage;
}

public boolean hasMoreMessages(int page, int pageSize) {
    int totalMessages = (int) chatMessageRepository.count(); // Get the total number of chat messages
    int totalPages = (int) Math.ceil((double) totalMessages / pageSize); // Calculate the total number of pages

    return page < totalPages; // Return true if there are more pages after the current page
}


}
