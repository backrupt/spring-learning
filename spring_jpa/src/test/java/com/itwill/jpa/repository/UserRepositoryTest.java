package com.itwill.jpa.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.SpringJpaApplicationTests;
import com.itwill.jpa.entity.User;

import jakarta.transaction.Transactional;

class UserRepositoryTest extends SpringJpaApplicationTests{
	@Autowired
	UserRepository userRepository;
	
	@DisplayName("사용자정의select")
	@Test
	void customSelect() {
		System.out.println(">>>findByNameSQL:"+userRepository.findByNameSQL("김경호11"));
		System.out.println(">>>findByNameLike:"+userRepository.findByNameLike("김경호1"+"%").size());
		System.out.println(">>>findByNameLike:"+userRepository.findByNameLike("%"+"경호"+"%").size());
		System.out.println(">>>findByNameAndEmail:"+userRepository.findByNameAndEmail("김경호20","guard20@gmail.com"));
		System.out.println(">>>findByNameOrEmail:"+userRepository.findByNameOrEmail("김경호20","guard21@gmail.com"));
		System.out.println(">>>findTop2ByNameOrderByUserIdDesc:"+userRepository.findTop2ByNameOrderByUserIdDesc("김경호20"));
		System.out.println(">>>findFirst3ByNameOrderByUserIdDesc:"+userRepository.findFirst3ByNameOrderByUserIdDesc("김경호20").size());
	}
	
	@DisplayName("회원가입")
	@Test
	void save() {
		User user1 = User.builder()
				.userId("dlsgh26")
				.password("2626")
				.name("최인호26")
				.email("dlsgh26@naver.com")
				.build();
		User user2 = User.builder()
				.userId("dlsgh27")
				.password("2727")
				.name("최인호27")
				.email("dlsgh27@naver.com")
				.build();
		User savedUser1 = userRepository.save(user1);
		User savedUser2 = userRepository.save(user2);
		System.out.println(savedUser1);
		System.out.println(savedUser2);
	}
	@DisplayName("회원아이디로찾기")
	@Test
	void findById() {
		Optional<User> optionalUser = userRepository.findById("guard1");
		if(optionalUser.isPresent()) {
			User findUser1 = optionalUser.get();
			System.out.println(findUser1);
		}
	}
	@DisplayName("회원수정")
	@Test
	@Transactional
	@Rollback(false)
	void update() {
		User findUser1 = userRepository.findById("guard1").get();
		findUser1.setName("언덕위에제임스딘");
		findUser1.setEmail("james@gmail.com");
		userRepository.save(findUser1);
	}
	@DisplayName("회원삭제")
	@Test
	void delete() {
		userRepository.deleteById("guard24");
		userRepository.delete(userRepository.findById("guard25").get());
	}

}
