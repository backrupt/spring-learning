package com.itwill.jpa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.jpa.entity.Product;
import com.itwill.jpa.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getProductList(){
		List<Product> products = productService.products();
		return ResponseEntity.status(HttpStatus.OK).body(products);
	}
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable(name = "productId") Long productId){
		Product product = productService.getProduct(productId);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}
	@Operation(description = "제품생성",summary = "제품생성")
	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
	}
	@Operation(description = "제품수정",summary = "제품수정")
	@PutMapping
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws Exception{
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.updateProduct(product));
	}
	@Operation(description = "제품삭제",summary = "제품삭제")
	@DeleteMapping("/{productId}")
	public ResponseEntity<Map> deleteProduct(@PathVariable(name = "productId") Long productId) throws Exception{
		productService.deleteProduct(productId);
		return ResponseEntity.status(HttpStatus.CREATED).body(new HashMap<>());
	}

	
}



