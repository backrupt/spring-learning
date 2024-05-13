package com.itwill.jpa.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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
	@OneToOne(mappedBy = "admin")
	private ChatRoom chatroom;

}
