package com.itwill.guest.entity;
/*
VO(Value Object),DTO(Data Transfer Object)
	- guest 테이블 1개 row의 데이타의 값을 가지는객체
	- guest 테이블 1개 row의 데이타값을 이동(파라메타,리턴데이타)시키기위한객체 
	- guest 테이블의 컬럼과 동일한수의 멤버변수를가지는객체
*/
/*
이름             널?       유형             
-------------- -------- -------------- 
GUEST_NO       NOT NULL NUMBER(10)     
GUEST_NAME              VARCHAR2(50)   
GUEST_DATE              DATE           
GUEST_EMAIL             VARCHAR2(50)   
GUEST_HOMEPAGE          VARCHAR2(50)   
GUEST_TITLE             VARCHAR2(100)  
GUEST_CONTENT           VARCHAR2(4000) 
*/

import java.util.Date;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@DynamicUpdate
@DynamicInsert
@Entity
public class Guest {
	@Id
	@SequenceGenerator(name = "guest_guest_no_seq",sequenceName = "guest_guest_no_seq",initialValue = 1,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "guest_guest_no_seq") //AUTO도 있다  
	private Integer guestNo;
	private String guestName;
	//@Column(columnDefinition = "date default sysdate")
	@ColumnDefault("sysdate")//default값 설정
	//@CreationTimestamp  //JPA에서 insert시 자동삽입
	private Date guestDate;
	private String guestEmail;
	private String guestHomepage;
	private String guestTitle;
	private String guestContent;
}

