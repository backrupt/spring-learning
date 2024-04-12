package com.itwill.jpa.entity;

import java.time.LocalDateTime;


public class Board {
	private Long boardno;
	private String title;
	private String writer;
	private String content;
	private LocalDateTime regdate;
	private Long readcount;
	private Long groupno;
	private Long step;
	private Long depth;
}
