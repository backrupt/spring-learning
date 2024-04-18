package com.itwill.guest;

import java.util.List;

import com.itwill.guest.entity.Guest;

public interface GuestDao {

	int insert(Guest guest) throws Exception;

	int update(Guest guest) throws Exception;

	int delete(int guestNo) throws Exception;

	Guest findByGuestNo(int guestNo) throws Exception;

	List<Guest> findByAll() throws Exception;

}