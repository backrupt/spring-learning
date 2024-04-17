package com.itwill.jpa.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.annotation.Generated;
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

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "product_seq")
	@SequenceGenerator(name = "product_seq",initialValue = 1,allocationSize = 1)
    private Long productId;
	private String name;
    private Integer price;
    private Integer stock;
  	@CreationTimestamp //JPA에서 날짜 최초한번 자동 insert
  	@ColumnDefault("sysdate") // column default
    private LocalDateTime createdAt;
    @UpdateTimestamp 	//JPA에서 수정시 자동 update
    private LocalDateTime updatedAt;
}
