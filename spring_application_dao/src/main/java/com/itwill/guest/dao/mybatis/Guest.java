package com.itwill.guest.dao.mybatis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
이름             널?       유형             
-------------- -------- -------------- 
GUEST_NO       NOT NULL NUMBER(10)     
GUEST_NAME     NOT NULL VARCHAR2(50)   
GUEST_DATE     NOT NULL DATE           
GUEST_EMAIL             VARCHAR2(50)   
GUEST_HOMEPAGE          VARCHAR2(50)   
GUEST_TITLE    NOT NULL VARCHAR2(100)  
GUEST_CONTENT  NOT NULL VARCHAR2(4000) 
*/

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter@Getter
public class Guest {
	private int guestNo;
	private String guestName;
	private String guestDate;
	private String guestEmail;
	private String guestHomepage;
	private String guestTitle;
	private String guestContent;

	
	
}
