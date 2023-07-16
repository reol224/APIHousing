package com.conestoga.APIHousing.controller;

// ChatMessageController.java


import com.conestoga.APIHousing.model.ChatMessage;
import com.conestoga.APIHousing.service.ChatMessageService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/chat")
public class ChatMessageController {
    private final ChatMessageService chatMessageService;

    @Autowired
    public ChatMessageController(ChatMessageService chatMessageService) {
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ChatMessage>> getAllChatMessages() {
        List<ChatMessage> chatMessages = chatMessageService.getAllChatMessages();
        return new ResponseEntity<>(chatMessages, HttpStatus.OK);
    }




    @PostMapping
    public ResponseEntity<ChatMessage> saveChatMessage(@RequestBody ChatMessage chatMessage) {
        ChatMessage savedChatMessage = chatMessageService.saveChatMessage(chatMessage);
        return new ResponseEntity<>(savedChatMessage, HttpStatus.CREATED);
    }
}
