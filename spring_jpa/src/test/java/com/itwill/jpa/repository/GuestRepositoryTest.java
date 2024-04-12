package com.itwill.jpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.SpringJpaApplicationTests;
import com.itwill.jpa.entity.Guest;

class GuestRepositoryTest extends SpringJpaApplicationTests{
	@Autowired
	GuestRepository guestRepository;
	
	void init() {
		Guest saveGuest1 = Guest.builder()
				//.guestNo(1L)
				.guestName("최인호1")
				.guestEmail("dlsgh1@naver.com")
				//.guestDate(new Date())
				.guestTitle("제목1")
				.guestHomepage("naver1.com")
				.guestContent("내용1")
				.build();
		Guest guest1 = guestRepository.save(saveGuest1);
		
		Guest saveGuest2 = Guest.builder()
				//.guestNo(2L)
				.guestName("최인호2")
				.guestEmail("dlsgh2@naver.com")
				//.guestDate(new Date())
				.guestTitle("제목2")
				.guestHomepage("naver2.com")
				.guestContent("내용2")
				.build();
		Guest guest2 = guestRepository.save(saveGuest2);
	}
	@Test
	@DisplayName("방명록저장")
	void save() {		
		init();
	}
	
	@Test
	@DisplayName("방명록조회")
	void select() {
		init();
		System.out.println("-------------------------findById-----------------------------");
		Optional<Guest> optional = guestRepository.findById(1L);
		if(optional.isPresent()) {
			Guest guest = optional.get();
			System.out.println(guest);
		}else {
			System.out.println("guest 없음");
		}
		System.out.println(guestRepository.findById(2L).get());
		
		System.out.println("-------------------------findAll-----------------------------");
		List<Guest> guestList = guestRepository.findAll();
		System.out.println(guestList);
		
		System.out.println("-------------------------count-----------------------------");
		System.out.println(guestRepository.count());
		
	}
	@Test
	@DisplayName("방명록수정")
	void update() {
		init();
		Guest guest1 = guestRepository.findById(1L).get();
		System.out.println("guest 1L :"+guest1);
		guest1.setGuestName("제임스딘");
		guestRepository.save(guest1);
	}
	
	@Test
	@Disabled
	@DisplayName("방명록삭제")
	void delete() {
		/*
		init();
		guestRepository.deleteById(1L);
		Guest deleteGuest = Guest.builder()
									.guestNo(2L)
									.build();
		guestRepository.delete(deleteGuest);
		*/
		init();
		guestRepository.deleteAll();
	}
	@DisplayName("사용자정의메소드")
	@Test
	void custom_method() throws ParseException {
		System.out.println(">>>"+guestRepository.findByGuestNoBetween(20L,30L));
		System.out.println(">>>"+guestRepository.findByGuestDateGreaterThan(new SimpleDateFormat("yyyy/MM/dd").parse("2024/04/12")).size());		
		System.out.println(">>>"+guestRepository.findByGuestDateGreaterThanEqual(new SimpleDateFormat("yyyy/MM/dd").parse("2024/04/12")).size());		
	}
}
