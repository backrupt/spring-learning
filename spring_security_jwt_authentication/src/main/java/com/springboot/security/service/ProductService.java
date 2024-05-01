package com.springboot.security.service;

import com.springboot.security.data.dto.ProductDto;


import java.util.List;

public interface ProductService {

	ProductDto getProduct(Long number);

	List<ProductDto> getProductList();

	ProductDto saveProduct(ProductDto productDto);

	ProductDto changeProduct(ProductDto productDto) throws Exception;

	void deleteProduct(Long number) throws Exception;

}