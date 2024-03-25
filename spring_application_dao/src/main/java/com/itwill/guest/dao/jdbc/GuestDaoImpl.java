package com.itwill.guest.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class GuestDaoImpl implements GuestDao {
	
	@Autowired
	private DataSource dataSource;
	
	public GuestDaoImpl() {
		System.out.println("2.#### GuestDaoImpl() 기본생성자호출");
	}
	
	
	@Override
	public ArrayList<Guest> selectAll()
			throws Exception{
		ArrayList<Guest> guestList=new ArrayList<Guest>();
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(GuestSQL.GUEST_SELECT_ALL);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			guestList.add(new Guest(rs.getInt("guest_no"), 
									rs.getString("guest_name"), 
									rs.getString("guest_date"),
									rs.getString("guest_email"),
									rs.getString("guest_homepage"),
									rs.getString("guest_title"),
									rs.getString("guest_content")));
		}
		con.close();
		return guestList;
	}
	
	@Override
	public Guest selectByNo(int no)throws Exception{
		Guest guest=null;
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(GuestSQL.GUEST_SELECT_BY_NO);
		pstmt.setInt(1, no);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			guest=new Guest(rs.getInt("guest_no"), 
					rs.getString("guest_name"), 
					rs.getString("guest_date"),
					rs.getString("guest_email"),
					rs.getString("guest_homepage"),
					rs.getString("guest_title"),
					rs.getString("guest_content"));
		}
		con.close();
		return guest;
	}
	@Override
	public int insertGuest(Guest guest)throws Exception {
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(GuestSQL.GUEST_INSERT);
		pstmt.setString(1, guest.getGuest_name());
		pstmt.setString(2, guest.getGuest_email());
		pstmt.setString(3, guest.getGuest_homepage());
		pstmt.setString(4, guest.getGuest_title());
		pstmt.setString(5, guest.getGuest_content());
		int rowCount=pstmt.executeUpdate();
		con.close();
		return rowCount;
		
	}
	@Override
	public int updateGuest(Guest guest)throws Exception {
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(GuestSQL.GUEST_UPDATE);
		pstmt.setString(1, guest.getGuest_name());
		pstmt.setString(2, guest.getGuest_email());
		pstmt.setString(3, guest.getGuest_homepage());
		pstmt.setString(4, guest.getGuest_title());
		pstmt.setString(5, guest.getGuest_content());
		pstmt.setInt(6, guest.getGuest_no());
		int rowCount=pstmt.executeUpdate();
		con.close();
		return rowCount;
		
	}
	@Override
	public int deleteGuest(int no)throws Exception {
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(GuestSQL.GUEST_DELETE);
		pstmt.setInt(1, no);
		int rowCount=pstmt.executeUpdate();
		con.close();
		return rowCount;
	}
	
	
	
}
