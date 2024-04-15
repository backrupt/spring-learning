package com.itwill.jpa.relation.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKey;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private Integer price;
	private Integer stock;

	/*
	 *  N(product):1(category)
	 *  OWNER TABLE
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	//@JoinColumn(name = "category_id")
	@ToString.Exclude
	private Category category;
	/*
	 * 1:1 OWNER TABLE
	 */
	@OneToOne(mappedBy = "product",cascade = CascadeType.PERSIST)
	@ToString.Exclude
	private ProductDetail productDetail;

	
	

}
