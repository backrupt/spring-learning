package com.itwill.jpa.relation.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "provider")
public class Provider {
	@Id
	@SequenceGenerator(name = "provider_seq",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "provider_seq")
	private Long id;
	private String name;
	/*
	 *	1(provider):N(product)
	 *	OWNER TABLE(X) 
	 */
	@OneToMany(mappedBy = "provider",cascade = {CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.LAZY)
	@ToString.Exclude
	@Builder.Default
	private List<Product> products=new ArrayList<>();
}
