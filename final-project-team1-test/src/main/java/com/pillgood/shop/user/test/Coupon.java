package com.pillgood.shop.user.test;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.pillgood.shop.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Coupon {
	@Id
	@SequenceGenerator(name = "coupon_no_seq",sequenceName = "coupon_no_seq",initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "coupon_no_seq")
	private Long no;
	private String name;
	//할인금액
	private Long deducatedPrice;
	//발행일
	@CreationTimestamp
	private Date createdDate;
	//만료일
	private Date expiredDate;
	//사용가능조건
	private boolean vaildCondition;
	//사용여부
	private boolean status;
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "user_no")
	@ToString.Exclude
	private User user;
}
