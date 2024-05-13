package com.itwill.jpa.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Entity
public class User {
	@Id
	@SequenceGenerator(name = "users_no_seq",sequenceName = "users_no_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "users_no_seq")
	@Column(name = "users_no")
	private Long no;
	private String id;
	private Long password;
	private String name;
	private Date birthday;
	private String gender;
	private String phone;
	private String provider;
	@Builder.Default
	private boolean deleted=false;
	@OneToMany(mappedBy = "user")
	@Builder.Default
	private List<Address> address = new ArrayList<>();
	@OneToMany(mappedBy = "user")
	@Builder.Default
	private List<Survey> survey = new ArrayList<>();
	@OneToMany(mappedBy = "user")
	@Builder.Default
	private List<Coupon> coupon = new ArrayList<>();
	@OneToOne(mappedBy = "user")
	private ChatRoom chatRoom;
	
}
