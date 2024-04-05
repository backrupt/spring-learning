package com.itwill.guest.mapper;

import java.util.List;

import org.apache.catalina.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.itwill.guest.Guest;
@Mapper
public interface GuestMapper {
	
	@SelectKey(before = true, resultType = Integer.class,keyProperty = "guestNo" ,statement ="select guest_guest_no_seq.nextval from dual")
	@Insert("insert into guest values(#{guestNo},#{guestName},sysdate,#{guestEmail},#{guestHomepage},#{guestTitle},#{guestContent})")
	int insert(Guest guest) throws Exception;
	
	@Update("update guest set guest_name=#{guestName},guest_email=#{guestEmail},guest_homepage=#{guestHomepage},guest_title=#{guestTitle},guest_content=#{guestContent} where guest_no=#{guestNo}")
	int update(Guest guest) throws Exception;
	
	@Delete("delete from guest where guest_no=#{guestNo}")
	int delete(@Param("guestNo") int guestNo) throws Exception;
	
	@Select("select * from guest where guest_no=#{guestNo}")
	Guest findByGuestNo(int guestNo) throws Exception;
	
	@ResultType(Guest.class)
	@Select("select * from guest")
	List<Guest> findByAll() throws Exception;
	
}
