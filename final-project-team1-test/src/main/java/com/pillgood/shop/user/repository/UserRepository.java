package com.pillgood.shop.user.repository;

import java.util.Optional;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import com.pillgood.shop.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	//로그인
	Optional<User> findByIdAndPassword(String id,String password);
	//아이디찾기
	Optional<User> findByPhone(String phone);
	//비밀번호찾기
	Optional<User> findById(String id);
	//회원탈퇴
	@SQLDelete(sql = "UPDATE users SET deleted = 1 WHERE no = ?")
	int deleteByNo(Long no);
	
	
}
