package com.itwill.jpa.relation.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.SpringJpaApplicationTests;
import com.itwill.jpa.relation.entity.Address;
import com.itwill.jpa.relation.entity.Student;
import com.itwill.jpa.relation.entity.Tutor;

class AddressRepositoryTest extends SpringJpaApplicationTests{
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	StudentRepository studentRepository;
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void savAddressWithStudents() {
		
		
	}
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void savAddressWithTutors() {
		
		
		
	}
	@Test
	//@Disabled
	@Transactional
	@Rollback(false)
	void selectAddressWithStudents() {
		Address address9 = addressRepository.findById(9L).get();
		System.out.println(">>>"+address9);
		System.out.println(">>>"+address9.getStudents());
	}

}
