package com.itwill.jpa.relation.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	/*
	  이름 	널? 		유형 
	  ------- -------- ------------ 
	  ADDR_ID 	NOT NULL NUMBER(11) 
	  STREET 	NOT NULL VARCHAR2(50) 
	  CITY 	NOT NULL VARCHAR2(50) 
	  STATE 	NOT NULL VARCHAR2(50) 
	  ZIP				VARCHAR2(10) 
	  COUNTRY NOT NULL VARCHAR2(50)
	 */
	@Id
	@SequenceGenerator(name = "ADDRESS_ADDR_ID_SEQ", sequenceName = "ADDRESS_ADDR_ID_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_ADDR_ID_SEQ")
	private Long addrId;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	@OneToMany(mappedBy = "address",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@Builder.Default
	@ToString.Exclude
	private List<Student> students = new ArrayList<>();
	
	@OneToMany(mappedBy = "address",cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
	@Builder.Default
	@ToString.Exclude
	private List<Tutor> tutors = new ArrayList<>();
	
	
	
	
	
	
	
	
}
