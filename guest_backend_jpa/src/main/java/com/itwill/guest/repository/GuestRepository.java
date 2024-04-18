package com.itwill.guest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.guest.entity.Guest;

public interface GuestRepository extends JpaRepository<Guest, Integer>{

}
