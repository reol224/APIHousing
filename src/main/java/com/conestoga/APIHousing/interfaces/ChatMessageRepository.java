package com.conestoga.APIHousing.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.conestoga.APIHousing.model.ChatMessage;

import java.util.List;
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    //get all messages
    List<ChatMessage> findAll();
    
}
