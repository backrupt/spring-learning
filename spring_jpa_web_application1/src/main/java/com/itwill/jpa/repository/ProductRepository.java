package com.itwill.jpa.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itwill.jpa.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
