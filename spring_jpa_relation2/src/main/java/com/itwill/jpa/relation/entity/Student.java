package com.itwill.jpa.relation.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Student {
	/*
	이름      널?       유형           
	------- -------- ------------ 
	STUD_ID NOT NULL NUMBER(11)   
	NAME    NOT NULL VARCHAR2(50) 
	EMAIL   NOT NULL VARCHAR2(50) 
	PHONE            VARCHAR2(15) 
	DOB              DATE         
	ADDR_ID          NUMBER(11)   
	*/
	@Id
	@SequenceGenerator(name = "STUDENT_STUD_ID_SEQ", sequenceName = "STUDENT_STUD_ID_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_STUD_ID_SEQ")
	private Long studId;
	@Column(nullable = false)
	private String name;
	
	private String email;
	private String phone;
	private LocalDateTime dob;
	
	@ManyToOne(cascade = {CascadeType.PERSIST},fetch = FetchType.LAZY)
	@JoinColumn(name = "addr_id")
	@ToString.Exclude
	@Builder.Default
	private Address address = new Address();
	
	@ToString.Exclude
	@Builder.Default
	@OneToMany(mappedBy = "student")
	private List<CourseEnrollment> courseEnrollments = new ArrayList<>();
	
		
	
	
	



}
