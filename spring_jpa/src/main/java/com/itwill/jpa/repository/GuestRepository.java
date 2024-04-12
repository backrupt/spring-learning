package com.itwill.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itwill.jpa.entity.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long>{
	//find,select,read,search....
	Long removeByGuestName(String guestName);
	Long countByGuestName(String guestName);
	Boolean existsByGuestName(String guestName);
	
	List<Guest> findByGuestNoBetween(Long no1,Long no2);
	
	List<Guest> findByGuestName(String guestName);
	
	List<Guest> findByGuestNameOrderByGuestNoDesc(String guestName);
	
	List<Guest> findByGuestNameOrGuestContent(String guestName,String guestContent);
	
	List<Guest> findByGuestDateGreaterThan(Date guestDate);
	
	List<Guest> findByGuestDateGreaterThanEqual(Date guestDate);
}
