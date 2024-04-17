package com.itwill.jpa.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.dto.ProductDto;
import com.itwill.jpa.entity.Product;
import com.itwill.jpa.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductDto getProduct(Long productId) {
		Product productEntity = productRepository.findById(productId).get();
		return ProductDto.toDto(productEntity);
	}

	@Override
	public ProductDto saveProduct(ProductDto productDto) {
		Product productEntity = productRepository.save(Product.toEntity(productDto)); 
		return ProductDto.toDto(productEntity);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto) throws Exception {
		Product productEntity = productRepository.save(Product.toEntity(productDto));
		return ProductDto.toDto(productEntity);
	}

	@Override
	public void deleteProduct(Long productId) throws Exception {
		productRepository.deleteById(productId);
	}

	@Override
	public List<ProductDto> products() {
		List<Product> productEntityList = productRepository.findAll();
		List<ProductDto> productDtoList = new ArrayList<>();
		for(Product productEntity:productEntityList) {
			productDtoList.add(ProductDto.toDto(productEntity));
		}
		return productDtoList;
	}

}
