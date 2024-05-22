package com.pillgood.shop.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.shop.chat.entity.ChatRoom;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>{

}
