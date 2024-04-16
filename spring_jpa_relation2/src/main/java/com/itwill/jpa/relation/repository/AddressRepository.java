package com.itwill.jpa.relation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.relation.entity.Address;
import com.itwill.jpa.relation.entity.Student;

public interface AddressRepository extends JpaRepository<Address, Long>{
	
}
