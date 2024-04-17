package com.itwill.jpa.dto;

import com.itwill.jpa.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
	private Long productId;
	private String name;
	private int price;
	private int stock;
	
	public static ProductDto toDto(Product productEntity) {
		return ProductDto.builder()
				.productId(productEntity.getProductId())
				.name(productEntity.getName())
				.price(productEntity.getPrice())
				.stock(productEntity.getStock())
				.build();
	}
}
