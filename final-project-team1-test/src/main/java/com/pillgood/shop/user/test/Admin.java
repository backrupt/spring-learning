package com.pillgood.shop.user.test;

import java.util.List;

import com.pillgood.shop.chat.entity.ChatRoom;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {
	@Id
	@SequenceGenerator(name = "admin_no_seq",sequenceName = "admin_no_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "admin_no_seq")
	private Long no;
	private String id;
	private Long password;
	private String role;
	@Builder.Default
	private boolean deleted=false;
	@OneToMany(mappedBy = "admin",cascade = CascadeType.ALL)
	private List<ChatRoom> chatroom;

}
