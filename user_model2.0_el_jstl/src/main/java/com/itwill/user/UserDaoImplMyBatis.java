package com.itwill.user;

import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.itwill.user.mapper.UserMapper;

public class UserDaoImplMyBatis implements UserDao {

	private SqlSessionFactory sqlSessionFactory;

	public UserDaoImplMyBatis() throws Exception {
		this.sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(Resources.getResourceAsStream("mybatis-config.xml"));
	}

	@Override
	public int update(User user) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : update() 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		int rowCount=userMapper.update(user);
		sqlSession.close();
		return rowCount;
	}

	@Override
	public User findUser(String userId) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : findUser() 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		User user=userMapper.findUser(userId);
		sqlSession.close();
		return user;
	}

	@Override
	public List<User> findUserList() throws Exception {
		System.out.println("#### UserDaoImplMyBatis : findUserList 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		List<User> userList=userMapper.findUserList();
		sqlSession.close();
		return userList;
	}

	@Override
	public int insert(User user) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : insert() 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		int rowCount=userMapper.insert(user);
		sqlSession.close();
		return rowCount;
	}

	@Override
	public int delete(String userId) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : delete() 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		int rowCount=userMapper.delete(userId);
		sqlSession.close();
		return rowCount;
	}

	@Override
	public int countByUserId(String userId) throws Exception {
		System.out.println("#### UserDaoImplMyBatis : countByUserId 호출  ");
		SqlSession sqlSession= sqlSessionFactory.openSession(true);
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		int rowCount=userMapper.countByUserId(userId);
		sqlSession.close();
		return rowCount;
	}

}