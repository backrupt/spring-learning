package com.itwill.guest.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.itwill.guest.dao.mybatis.Guest;
@Mapper
public interface GuestMapper {
	
	List<Guest> selectAll() throws Exception;
	Guest selectByNo(int no) throws Exception;
	int insertGuest(Guest guest) throws Exception;
	int updateGuest(Guest guest) throws Exception;
	int deleteGuest(int no) throws Exception;
}