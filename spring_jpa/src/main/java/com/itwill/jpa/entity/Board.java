package com.itwill.jpa.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(name = "board_boardno_seq",sequenceName = "board_boardno_seq",initialValue = 1,allocationSize = 1)
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "board_boardno_seq")
	private Long boardno;
	private String title;
	private String writer;
	private String content;
	@Column(updatable = false) 
	@ColumnDefault("sysdate")	//JPA insert시 자동으로 값을 안채워줌
	@CreationTimestamp			//JPA insert시 자동으로 값을 채워줌
	private LocalDateTime regdate;
	@ColumnDefault("0")			//JPA insert시 자동으로 값을 안채워줌
	private Long readcount;
	@ColumnDefault("0")			//JPA insert시 자동으로 값을 안채워줌
	private Long groupno;
	@ColumnDefault("1")			//JPA insert시 자동으로 값을 안채워줌
	private Long step;
	@ColumnDefault("0")			//JPA insert시 자동으로 값을 안채워줌
	private Long depth;
}
