package com.itwill.security.user.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.security.user.entity.UserAuthority;
import com.itwill.security.user.entity.UserDetailsImpl;
import com.itwill.security.user.repository.UsersRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsersRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("1.loadUserByUsername");
		Optional<UserDetailsImpl> optionalUser = userRepository.findUserByUserid(username);
		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		System.out.println("2.User:" + optionalUser.get());
		return optionalUser.get();
	}

	public Optional<UserDetailsImpl> findByUserId(String userId) {
		return userRepository.findUserByUserid(userId);
	}

	public UserDetailsImpl createUser(UserDetailsImpl user) {
		return userRepository.save(user);
	}

	public void addAuthority(String userId, String authority) {
		Optional<UserDetailsImpl> optionalUserDetailsImpl= userRepository.findUserByUserid(userId);
		if(optionalUserDetailsImpl.isPresent()) {
			UserDetailsImpl userDetailsImpl =  optionalUserDetailsImpl.get();
			UserAuthority newRole = new UserAuthority(userDetailsImpl, authority);
			userDetailsImpl.getAuthorities().add(newRole);
			this.createUser(userDetailsImpl);
		}
	}

	
}