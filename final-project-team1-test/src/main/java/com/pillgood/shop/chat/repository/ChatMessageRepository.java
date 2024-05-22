package com.pillgood.shop.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.shop.chat.entity.ChatMessage;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long>{

}
