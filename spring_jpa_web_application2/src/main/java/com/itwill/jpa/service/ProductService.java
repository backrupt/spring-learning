package com.itwill.jpa.service;
import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.jpa.dto.ProductDto;
import com.itwill.jpa.entity.Product;


@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
public interface ProductService {
	ProductDto getProduct(Long productId);
	ProductDto saveProduct(ProductDto productDto);
	ProductDto updateProduct(ProductDto productDto) throws Exception;
	void deleteProduct(Long productId) throws Exception;
	List<ProductDto> products();
}