package com.itwill.guest.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.guest.dao.mybatis.mapper.GuestMapper;

import oracle.net.aso.f;


@Repository
public class GuestDaoImplMyBatis implements GuestDao{
	@Autowired
	private GuestMapper guestMapper;
	
	public GuestDaoImplMyBatis() {
		System.out.println("### GuestDaoImplMyBatis()생성자");
	}
	
	
	
	
	
	@Override
	public List<Guest> selectAll() throws Exception {
		return guestMapper.selectAll();
	}
	@Override
	public Guest selectByNo(int no) throws Exception {
		return guestMapper.selectByNo(no);
	}
	@Override
	public int insertGuest(Guest guest) throws Exception {
		return guestMapper.insertGuest(guest);
	}
	@Override
	public int updateGuest(Guest guest) throws Exception {
		return guestMapper.updateGuest(guest);
	}
	@Override
	public int deleteGuest(int no) throws Exception {
		return guestMapper.deleteGuest(no);
	}
}
