package com.itwill.jpa.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class ChatMessage {
	@Id
	@SequenceGenerator(name = "chat_message_no_seq",sequenceName = "chat_message_no_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "chat_message_no_seq")
	private Long no;
	private String senderId;
	private String message;
	@CreationTimestamp
	private Date createdDate;
	@ManyToOne
	@JoinColumn(name = "chat_room_no")
	@ToString.Exclude
	private ChatRoom chatRoom;
}
