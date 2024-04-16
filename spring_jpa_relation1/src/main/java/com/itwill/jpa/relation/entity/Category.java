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
import jakarta.persistence.SequenceGenerator;
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
	@SequenceGenerator(name = "category_seq",sequenceName = "category_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "category_seq")
	private Long id;
	@Column(unique = true,nullable = false)
	private String code;
	private String name;
	
	/*
	 *  1(category):N(product)
	 *  OWNER TABLE X 
	 */
	@OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	@ToString.Exclude
	@Builder.Default
	private List<Product> products=new ArrayList<>();
}
