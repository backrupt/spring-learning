package com.pillgood.shop.user.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.pillgood.shop.chat.entity.ChatRoom;
import com.pillgood.shop.user.test.Address;
import com.pillgood.shop.user.test.Coupon;
import com.pillgood.shop.user.test.Survey;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.FetchType;
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
@Where(clause = "deleted = 0")
@SQLDelete(sql = "UPDATE users SET deleted = 1 WHERE no = ?")
public class User {
	@Id
	@SequenceGenerator(name = "users_no_seq",sequenceName = "users_no_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "users_no_seq")
	private Long no;
	@Column(unique = true)
	private String id;
	private String password;
	private String name;
	private Date birthday;
	private String gender;
	private String phone;
	private String provider;
	@Builder.Default
	private boolean deleted=false;
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	@Builder.Default
	private List<Address> address = new ArrayList<>();
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	@Builder.Default
	private List<Survey> survey = new ArrayList<>();
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
	@Builder.Default
	private List<Coupon> coupon = new ArrayList<>();
	@OneToOne(mappedBy = "user")
	private ChatRoom chatRoom;
}
