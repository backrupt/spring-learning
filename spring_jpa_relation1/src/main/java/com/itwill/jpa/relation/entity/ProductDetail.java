package com.itwill.jpa.relation.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ProductDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_detail_seq")
	@SequenceGenerator(name = "product_detail_seq",allocationSize = 1)
	private Long id;
	private String description;
	/*
	 * 1:1 OWNER TABLE
	 */
	@OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@ToString.Exclude
	private Product product;
	
	
}
