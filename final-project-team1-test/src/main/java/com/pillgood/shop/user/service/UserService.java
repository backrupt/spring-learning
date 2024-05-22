package com.pillgood.shop.user.service;

import java.security.SecureRandom;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pillgood.shop.user.dto.UserDto;
import com.pillgood.shop.user.dto.UserLoginRequestDto;
import com.pillgood.shop.user.entity.User;
import com.pillgood.shop.user.repository.UserRepository;

import jakarta.transaction.Transactional;



@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private JwtUtil jwtUtil;

	//아이디 중복체크
	public boolean isExist(String id) {
		Optional<User> optional = userRepository.findById(id);
		return optional.isPresent();
	}
	
	//회원가입
	public UserDto createUser(UserDto userJoinRequestDto) {
		User user = userRepository.save(modelMapper.map(userJoinRequestDto, User.class));
		UserDto userResponseDto = modelMapper.map(user, UserDto.class); 
		return userResponseDto;
	}
	//회원로그인
	public boolean login(UserLoginRequestDto user) {
		Optional<User> optional = userRepository.findByIdAndPassword(user.getId(),user.getPassword());
		return optional.isPresent();
	}
	//아이디찾기
	public UserDto findUserId(String phone) {
		Optional<User> optional = userRepository.findByPhone(phone);
		User user = new User();
		if(optional.isPresent()) {
		user = optional.get();
		}
		return modelMapper.map(user, UserDto.class); 
	}
	//비밀번호찾기
	public UserDto findUserPassword(String id) {
		Optional<User> optional = userRepository.findById(id);
		User user = new User();
		if(optional.isPresent()) {
		user = optional.get();
		}
		return modelMapper.map(user, UserDto.class); 
	}
	//비밀번호 임시비밀번호로 변경
	public String updateUserPassword(String id) {
		User user = userRepository.findById(id).get();
		int tempPassword=createTempPassword();
		user.setPassword(tempPassword+"");
		userRepository.save(user);
		return tempPassword+"";
	}
	//회원상세보기
	public UserDto userView(String id) {
		 Optional<User> optional = userRepository.findById(id);
		 User user = new User();
			if(optional.isPresent()) {
			user = optional.get();
			}
		 return modelMapper.map(user, UserDto.class);
	}
	//회원수정
	public UserDto updateUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		return modelMapper.map(userRepository.save(user), UserDto.class);
	}
	//회원탈퇴
	@Transactional
	public int removeUser(Long no) {
		userRepository.deleteByNo(no);
		return 0;
	} 
	
	//임시비밀번호생성
	public int createTempPassword() {
	        SecureRandom secureRandom = new SecureRandom();
	        int upperLimit = (int) Math.pow(10,10);
	        return secureRandom.nextInt(upperLimit);
	}
		
	public String authenticate(String username, String password) {
		Optional optional = userRepository.findByIdAndPassword(username, password);
		if(optional.isPresent()) {
			return jwtUtil.generateToken(username);
		}
		return null;
	}
	
}
