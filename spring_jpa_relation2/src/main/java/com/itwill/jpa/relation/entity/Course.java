package com.itwill.jpa.relation.entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course  {
	/*
	이름          널?       유형            
	----------- -------- ------------- 
	COURSE_ID   NOT NULL NUMBER(11)    
	NAME        NOT NULL VARCHAR2(100) 
	DESCRIPTION          VARCHAR2(512) 
	START_DATE           DATE          
	END_DATE             DATE          
	TUTOR_ID    NOT NULL NUMBER(11)    	
	 */
	@Id
	@SequenceGenerator(name = "COURSE_COURSE_ID_SEQ", sequenceName = "COURSE_COURSE_ID_SEQ", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSE_COURSE_ID_SEQ")
	private Long courseId;
	
	private String name;
	private String description;
	@CreationTimestamp
	private LocalDateTime startDate;
	@UpdateTimestamp
	@Builder.Default
	private LocalDateTime endDate=LocalDateTime.now().minusDays(30);
	
	@Builder.Default
	@ToString.Exclude
	@OneToMany(mappedBy = "course")
	private List<CourseEnrollment> courseEnrollments=new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tutor_iddd")
	@ToString.Exclude
	private Tutor tutor;
	
	
	
	
	
}
