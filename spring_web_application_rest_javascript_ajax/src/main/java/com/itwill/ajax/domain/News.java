package com.itwill.ajax.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class News {
	private int no;
	private String title;
	private String company;
	private String date;
	
	
}

