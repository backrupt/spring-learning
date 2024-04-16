package com.itwill.jpa.relation.repository;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.jpa.relation.SpringJpaRelationApplicationTests;
import com.itwill.jpa.relation.entity.Product;
import com.itwill.jpa.relation.entity.Provider;

import jakarta.transaction.Transactional;

class ProviderRepositoryTest extends SpringJpaRelationApplicationTests {
	@Autowired
	ProviderRepository providerRepository;
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void providerWithProductsSave() {
		Provider provider = Provider.builder().name("한빛미디어").build();
		Product product1 = Product.builder()
				.name("자바바")
				.price(3000)
				.stock(10)
				.build();
		Product product2 = Product.builder()
				.name("자바라")
				.price(2000)
				.stock(30)
				.build();
		Product product3 = Product.builder()
				.name("자바는")
				.price(1200)
				.stock(40)
				.build();
		System.out.println("----------save----------");
		/*
		 *  연관설정 Provider-->Product
		 *  OWNER TABLE(X)
		 *  양방향 하고싶으면 둘다 해야함
		 */
		provider.getProducts().add(product1);
		provider.getProducts().add(product2);
		provider.getProducts().add(product3);
		
		product1.setProvider(provider);
		product2.setProvider(provider);
		product3.setProvider(provider);
		providerRepository.save(provider);
	}
	
	@Test
	@Transactional
	@Rollback(false)
	@Disabled
	void providerWithProductsRead() {
		Provider provider = providerRepository.findById(1L).get();
		System.out.println(">>>:"+provider);
		System.out.println(">>>:"+provider.getProducts());
	}
	
	@Test
	@Transactional
	@Rollback(false)
	void providerWithProductsDelete() {
		Provider provider = providerRepository.findById(2L).get();
		System.out.println(">>>:"+provider);
		System.out.println(">>>:"+provider.getProducts());
		providerRepository.delete(provider);
	}
}
