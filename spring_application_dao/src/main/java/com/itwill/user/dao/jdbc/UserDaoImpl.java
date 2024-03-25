package com.itwill.user.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private DataSource dataSource;
	public UserDaoImpl() throws Exception {
		System.out.println("2.#### UserDaoImpl() 기본생성자호출");
	}
	public UserDaoImpl(DataSource dataSource) {
		System.out.println("2.#### UserDaoImpl(DataSource dataSource) 생성자호출");
		this.dataSource = dataSource;
	}
	
	public void setDataSource(DataSource dataSource) {
		System.out.println("3.#### UserDaoImpl.setDataSource(DataSource dataSource) 메쏘드호출");
		this.dataSource = dataSource;
	}
	/*
	 * 사용자관리테이블에 새로운사용자생성
	 */
	@Override
	public int create(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int insertRowCount = 0;
		try {
			/*
			 * 예외발생 예상코드
			 */
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_INSERT);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getEmail());
			insertRowCount = pstmt.executeUpdate();
		} finally {
			/*
			 * 예외발생과 관계없이 반듯시 실행되는 코드
			 */
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return insertRowCount;
	}
	/*
	 * 기존의 사용자정보를 수정
	 */
	@Override
	public int update(User user) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int updateRowCount = 0;
		try {
			/*
			 * 예외발생 예상코드
			 */
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_UPDATE);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getUserId());
			updateRowCount = pstmt.executeUpdate();
		} finally {
			/*
			 * 예외발생과 관계없이 반듯시 실행되는 코드
			 */
			if (pstmt != null) {
				pstmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return updateRowCount;
	}

	/*
	 * 사용자아이디에해당하는 사용자를 삭제
	 */
	@Override
	public int remove(String userId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		int removeRowCount = 0;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_REMOVE);
			pstmt.setString(1, userId);
			removeRowCount = pstmt.executeUpdate();

		} finally {
			/*
			 * 예외발생과 관계없이 반듯시 실행되는 코드
			 */
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();

		}
		return removeRowCount;
	}

	/*
	 * 사용자아이디에해당하는 정보를 데이타베이스에서 찾아서 User 도메인클래스에 저장하여 반환
	 */
	
	@Override
	public User findUser(String userId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User findUser = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_SELECT_BY_ID);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				findUser = new User(rs.getString("userid"), 
									rs.getString("password"), 
									rs.getString("name"),
									rs.getString("email"));

			}
		} finally {
			/*
			 * 예외발생과 관계없이 반듯시 실행되는 코드
			 */
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return findUser;
	}

	/*
	 * 모든사용자 정보를 데이타베이스에서 찾아서 List<User> 콜렉션 에 저장하여 반환
	 */
	@Override
	public ArrayList<User> findUserList() throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<User> findUserList = new ArrayList<User>();
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_SELECT_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				findUserList.add(new User(	rs.getString("userid"),
											rs.getString("password"), 
											rs.getString("name"),
											rs.getString("email")));

			}
		} finally {
			/*
			 * 예외발생과 관계없이 반듯시 실행되는 코드
			 */
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return findUserList;
	}

	/*
	 * 인자로 전달되는 아이디를 가지는 사용자가 존재하는지의 여부를판별
	 */
	@Override
	public boolean existedUser(String userId) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isExist = false;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement(UserSQL.USER_SELECT_BY_ID_COUNT);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			rs.next();
			int count = rs.getInt("cnt");
			if (count == 1) {
				isExist = true;
			}
		} finally {
			/*
			 * 예외발생과 관계없이 반듯시 실행되는 코드
			 */
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		}
		return isExist;
	}

}
