package com.itwill.jpa.relation.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.jpa.relation.SpringJpaRelationApplicationTests;
import com.itwill.jpa.relation.entity.Product;
import com.itwill.jpa.relation.entity.ProductDetail;

import jakarta.transaction.Transactional;

class ProductDetailRepositoryTest extends SpringJpaRelationApplicationTests{
	@Autowired
	ProductDetailRepository productDetailRepository;
	
	
	@Test
	/*@Rollback(false)
	@Transactional*/
	void productDetailWithProductSave() {
		Product product = Product.builder().name("JPA").price(2000).stock(50).build();
		ProductDetail productDetail = ProductDetail.builder()
				.description("아주좋은책이에용")
				.build();
									
		/*
		 연관관계설정(OWNER)
		 ProductDetail에 Product set
		*/
		productDetail.setProduct(product);
		productDetailRepository.save(productDetail);
		
	}
	
	@Test
	@Transactional
	void productDetailWithProductRead() {
		
		System.out.println("--------read-----------");
		ProductDetail findProductDetail = productDetailRepository.findById(1L).get();
		System.out.println(findProductDetail);
		Product findProduct = findProductDetail.getProduct();		
		System.out.println(findProduct);
	}
	
}
