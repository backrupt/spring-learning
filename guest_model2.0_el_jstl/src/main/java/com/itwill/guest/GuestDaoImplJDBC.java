package com.itwill.guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;



/*
 * Dao(Data[DataBase] Access Object)객체(클래스)
 *   - guest테이블에 CRUD(Create,Read,Update,Delet)작업을 하는 
 *     단위메쏘드를 가지고있는 객체(클래스)
 */
public class GuestDaoImplJDBC implements GuestDao {
	/*
	 * Connection생성,해제 객체
	 */
	private DataSource dataSource;
	public GuestDaoImplJDBC() throws Exception{
		/******Apache BasicDataSource*****/
		/*
		 * jdbc.properties 파일을 Properties객체로생성
		 */
		BasicDataSource basicDataSource=new BasicDataSource();
		Properties properties=new Properties();
		properties.load(GuestDaoImplJDBC.class.getResourceAsStream("/jdbc.properties"));
		basicDataSource.setDriverClassName(properties.getProperty("spring.datasource.driver-class-name"));
		basicDataSource.setUrl(properties.getProperty("spring.datasource.url"));
		basicDataSource.setUsername(properties.getProperty("spring.datasource.username"));
		basicDataSource.setPassword(properties.getProperty("spring.datasource.password"));
		basicDataSource.setInitialSize(1);
		basicDataSource.setMaxTotal(2);
		this.dataSource=basicDataSource;
	}
	
	@Override
	public int insert(Guest guest)throws Exception {
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt1=con.prepareStatement(GuestSQL.GUEST_INSERT_BEFORE_GUEST_NO);
		ResultSet rs1 = pstmt1.executeQuery();
		rs1.next();
		int new_guest_no = rs1.getInt(1);
		rs1.close();
		pstmt1.close();
		
		
		PreparedStatement pstmt2=con.prepareStatement(GuestSQL.GUEST_INSERT);
		pstmt2.setString(1, guest.getGuestName());
		pstmt2.setString(2, guest.getGuestEmail());
		pstmt2.setString(3, guest.getGuestHomepage());
		pstmt2.setString(4, guest.getGuestTitle());
		pstmt2.setString(5, guest.getGuestContent());
		int rowCount=pstmt2.executeUpdate();
		
		
		pstmt2.close();
		con.close();
		return new_guest_no;
	}
	@Override
	public int update(Guest guest) throws Exception{
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(GuestSQL.GUEST_UPDATE);
		pstmt.setString(1, guest.getGuestName());
		pstmt.setString(2, guest.getGuestEmail());
		pstmt.setString(3, guest.getGuestHomepage());
		pstmt.setString(4, guest.getGuestTitle());
		pstmt.setString(5, guest.getGuestContent());
		pstmt.setInt(6, guest.getGuestNo());
		int rowCount=pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return rowCount;
	}
	@Override
	public int delete(int guestNo) throws Exception{
		
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(GuestSQL.GUEST_DELETE);
		pstmt.setInt(1, guestNo);
		int rowCount = pstmt.executeUpdate();
		pstmt.close();
		con.close();
		return rowCount;
		
		
	}
	@Override
	public Guest findByGuestNo(int guestNo) throws Exception{
		Guest guest=null;
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(GuestSQL.GUEST_SELECT_BY_NO);
		pstmt.setInt(1, guestNo);
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()) {
			guest=new Guest(rs.getInt("guest_no"),
							rs.getString("guest_name"),
							rs.getDate("guest_date"), 
							rs.getString("guest_email"), 
							rs.getString("guest_homepage"), 
							rs.getString("guest_title"), 
							rs.getString("guest_content"));
		}
		rs.close();
		pstmt.close();
		con.close();
		return guest;
	}
	public List<Guest> findByGuestName(String guestName) throws Exception{
		List<Guest> guestList=new ArrayList<Guest>();
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(GuestSQL.GUEST_SELECT_BY_NAME);
		pstmt.setString(1, guestName);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			Guest guest=new Guest(rs.getInt("guest_no"),
					rs.getString("guest_name"),
					rs.getDate("guest_date"), 
					rs.getString("guest_email"), 
					rs.getString("guest_homepage"), 
					rs.getString("guest_title"), 
					rs.getString("guest_content"));
			guestList.add(guest);
		}
		rs.close();
		pstmt.close();
		con.close();
		return guestList;
	}
	public List<Guest> findByGuestTitle(String guestTitle) throws Exception{
		List<Guest> guestList=new ArrayList<Guest>();
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(GuestSQL.GUEST_SELECT_BY_TITLE);
		pstmt.setString(1, guestTitle);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			Guest guest=new Guest(rs.getInt("guest_no"),
					rs.getString("guest_name"),
					rs.getDate("guest_date"), 
					rs.getString("guest_email"), 
					rs.getString("guest_homepage"), 
					rs.getString("guest_title"), 
					rs.getString("guest_content"));
			guestList.add(guest);
		}
		rs.close();
		pstmt.close();
		con.close();
		return guestList;
	}
	public List<Guest> findByGuestContent(String guestContent) throws Exception{
		List<Guest> guestList=new ArrayList<Guest>();
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(GuestSQL.GUEST_SELECT_BY_CONTENT);
		pstmt.setString(1, guestContent);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			Guest guest=new Guest(rs.getInt("guest_no"),
					rs.getString("guest_name"),
					rs.getDate("guest_date"), 
					rs.getString("guest_email"), 
					rs.getString("guest_homepage"), 
					rs.getString("guest_title"), 
					rs.getString("guest_content"));
			guestList.add(guest);
		}
		rs.close();
		pstmt.close();
		con.close();
		return guestList;
	}
	@Override
	public List<Guest> findByAll() throws Exception {
		List<Guest> guestList=new ArrayList<Guest>();
		Connection con=dataSource.getConnection();
		PreparedStatement pstmt=con.prepareStatement(GuestSQL.GUEST_SELECT_ALL);
		ResultSet rs=pstmt.executeQuery();
		while(rs.next()) {
			guestList.add(new Guest(rs.getInt("guest_no"),
									rs.getString("guest_name"),
									rs.getDate("guest_date"), 
									rs.getString("guest_email"), 
									rs.getString("guest_homepage"), 
									rs.getString("guest_title"), 
									rs.getString("guest_content"))
					   );
			
		}
		rs.close();
		pstmt.close();
		con.close();
		return guestList;
	}

}

