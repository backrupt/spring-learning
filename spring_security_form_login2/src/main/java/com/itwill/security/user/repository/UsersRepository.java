package com.itwill.security.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.security.user.entity.UserDetailsImpl;

public interface UsersRepository extends JpaRepository<UserDetailsImpl, Long> {

    Optional<UserDetailsImpl> findUserByUserid(String userId);

}
