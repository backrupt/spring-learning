package com.itwill.guest;
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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Guest {
	
	private int guest_no;
	private String guest_name;
	private Date guest_date;
	private String guest_email;
	private String guest_homepage;
	private String guest_title;
	private String guest_content;	
	
}
