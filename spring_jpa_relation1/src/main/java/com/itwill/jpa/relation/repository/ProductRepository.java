package com.itwill.jpa.relation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.relation.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
