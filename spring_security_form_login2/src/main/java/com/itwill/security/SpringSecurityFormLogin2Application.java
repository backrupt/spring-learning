package com.itwill.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.itwill.security.user.entity.UserDetailsImpl;
import com.itwill.security.user.service.UserDetailsServiceImpl;

@SpringBootApplication
public class SpringSecurityFormLogin2Application implements CommandLineRunner {
	@Autowired
	private UserDetailsServiceImpl userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityFormLogin2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		if (userService.findByUserId("user1").isEmpty()) {
			UserDetailsImpl users1 = userService.createUser(UserDetailsImpl.builder().userid("user1").password(passwordEncoder.encode("1111"))
					.email("user1@gamil.com").enabled(true).build());
			userService.addAuthority(users1.getUserid(), "ROLE_USER");

		}

		if (userService.findByUserId("user2").isEmpty()) {
			UserDetailsImpl users2 = userService.createUser(UserDetailsImpl.builder().userid("user2").password(passwordEncoder.encode("2222"))
					.email("user2@gamil.com").enabled(true).build());

			userService.addAuthority(users2.getUserid(), "ROLE_USER");
		}

		if (userService.findByUserId("user3").isEmpty()) {
			UserDetailsImpl user3 = userService.createUser(UserDetailsImpl.builder().userid("user3").password(passwordEncoder.encode("3333"))
					.email("user3@gmail.com").enabled(true).build());
			userService.addAuthority(user3.getUserid(), "ROLE_ADMIN");
		}

	}

}
