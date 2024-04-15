package com.itwill.jpa.relation.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Category{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@Column(unique = true,nullable = false)
	private String code;
	private String name;
	
	/*
	 *  1(category):N(product)
	 *  OWNER TABLE X 
	 */
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST})
	//@ToString.Exclude
	@Builder.Default
	private List<Product> products=new ArrayList<>();
}
