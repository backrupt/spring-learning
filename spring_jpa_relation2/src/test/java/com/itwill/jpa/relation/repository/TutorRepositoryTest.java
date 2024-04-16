package com.itwill.jpa.relation.repository;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.SpringJpaApplicationTests;
import com.itwill.jpa.relation.entity.Address;
import com.itwill.jpa.relation.entity.Student;
import com.itwill.jpa.relation.entity.Tutor;

class TutorRepositoryTest extends SpringJpaApplicationTests{
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	TutorRepository tutorRepository;
	
	
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void tutorWithAddressSave() {
		
		Tutor tutor1=Tutor.builder()
					.name("김강사1")
					.email("kim1@gmail.com")
					.phone("111-1111")
					.dob(LocalDateTime.now().minusWeeks(1))
					.build();
		Tutor tutor2=Tutor.builder()
				.name("김강사2")
				.email("kim2@gmail.com")
				.phone("222-2222")
				.dob(LocalDateTime.now())
				.build();
		
		Address address=Address.builder()
				.street("서울호텔")
				.city("서울")
				.state("서울시")
				.zip("77777")
				.country("대한민국")
				.build();
		
		
		
		
		
	}
	@Test
	@Disabled
	@Transactional
	@Rollback(false)
	void selectTutorWithAddress() {
	
	}
	
	@Test
	//@Disabled
	@Transactional
	@Rollback(false)
	void selectTutorWithCourses() {
		Tutor tutor1 = tutorRepository.findById(1L).get();
		System.out.println(">>>"+tutor1);
		System.out.println(">>>"+tutor1.getCourses());
	}
}
