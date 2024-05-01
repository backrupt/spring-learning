package com.springboot.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.security.data.entity.User;
import com.springboot.security.data.repository.UserRepository;

@SpringBootApplication

public class SecurityApplication implements CommandLineRunner{
	@Autowired
	UserRepository userRepository;
	@Autowired 
	PasswordEncoder passwordEncoder;
    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		 User user1 = User.builder()
	                .uid("guard1")
	                .name("김경호1")
	                .password(passwordEncoder.encode("1111"))
	                .roles(Collections.singletonList("ROLE_USER"))
	                .build();
		 User user2 = User.builder()
				 .uid("guard2")
				 .name("김경호2")
				 .password(passwordEncoder.encode("2222"))
				 .roles(Collections.singletonList("ROLE_USER"))
				 .build();
		 User user3 = User.builder()
				 .uid("guard3")
				 .name("김경호3")
				 .password(passwordEncoder.encode("3333"))
				 .roles(Collections.singletonList("ROLE_ADMIN"))
				 .build();
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		
	}
    
}
