package com.itwill.guest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.guest.mapper.GuestMapper;
@Repository("guestDaoImplMyBatis")

public class GuestDaoImplMyBatis implements GuestDao {
	@Autowired
	private GuestMapper guestMapper;

	public GuestDaoImplMyBatis() {

	}

	@Override
	public int insert(Guest guest) throws Exception {
		guestMapper.insert(guest);
		return guest.getGuestNo();
	}

	@Override
	public int update(Guest guest) throws Exception {
		return guestMapper.update(guest);

	}

	@Override
	public int delete(int guestNo) throws Exception {
		return guestMapper.delete(guestNo);
	}

	@Override
	public Guest findByGuestNo(int guestNo) throws Exception {
		return guestMapper.findByGuestNo(guestNo);
	}

	@Override
	public List<Guest> findByAll() throws Exception {
		return guestMapper.findByAll();
	}

}
