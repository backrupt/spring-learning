package com.itwill.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.jpa.entity.Product;
import com.itwill.jpa.repository.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product getProduct(Long productId) {
		return productRepository.findById(productId).get();
	}

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) throws Exception {
		Product updatedProduct = productRepository.save(product);
		return updatedProduct;
	}

	@Override
	public void deleteProduct(Long productId) throws Exception {
		productRepository.deleteById(productId);
	}

	@Override
	public List<Product> products() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}
	
}
