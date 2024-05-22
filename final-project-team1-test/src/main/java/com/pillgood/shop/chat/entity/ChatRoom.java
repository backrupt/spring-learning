package com.pillgood.shop.chat.entity;

import java.util.ArrayList;
import java.util.List;

import com.pillgood.shop.user.entity.User;
import com.pillgood.shop.user.test.Admin;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ChatRoom {
	@Id
	@SequenceGenerator(name = "chat_room_no_seq",sequenceName = "chat_room_no_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "chat_room_no_seq")
	private Long no;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "user_no")
	@ToString.Exclude
	private User user;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_no")
	@ToString.Exclude
	private Admin admin;
	@Builder.Default
	@OneToMany(mappedBy = "chatRoom",cascade = CascadeType.ALL)
	@ToString.Exclude
	private List<ChatMessage> chatMessage = new ArrayList<>();
}
