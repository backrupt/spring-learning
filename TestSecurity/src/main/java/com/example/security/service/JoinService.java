package com.example.security.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.dto.JoinDTO;
import com.example.security.entity.User;
import com.example.security.repository.UserRepository;

@Service
public class JoinService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired 
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void joinProcess(JoinDTO joinDTO) {
		
		//db에 이미 동일한 id를 가진 회원이 존재하는지?
		
		joinDTO.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
		User user = modelMapper.map(joinDTO, User.class);
		user.setRole("ROLE_USER");
		
		userRepository.save(user);
	}
}
