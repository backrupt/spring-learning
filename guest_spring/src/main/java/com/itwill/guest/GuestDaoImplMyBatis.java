package com.itwill.guest;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itwill.guest.mapper.GuestMapper;

@Repository
public class GuestDaoImplMyBatis implements GuestDao{
	//private SqlSessionFactory sqlSessionFactory;
	@Autowired
	private GuestMapper guestMapper;
	public GuestDaoImplMyBatis() {
		/*
		try {
			InputStream myBatisConfigInputStream = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
			this.sqlSessionFactory = sqlSessionFactoryBuilder.build(myBatisConfigInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
	}
	@Override
	public int insert(Guest guest) throws Exception {
		//SqlSession sqlSession = sqlSessionFactory.openSession(true);
		//GuestMapper guestMapper=sqlSession.getMapper(GuestMapper.class);
		int rowCount=guestMapper.insert(guest);
		//sqlSession.close();
		return guest.getGuest_no();
	}

	@Override
	public int update(Guest guest) throws Exception {
		//SqlSession sqlSession = sqlSessionFactory.openSession(true);
		//GuestMapper guestMapper=sqlSession.getMapper(GuestMapper.class);
		return guestMapper.update(guest);
		//sqlSession.close();
	}

	@Override
	public int delete(int guestNo) throws Exception {
		//SqlSession sqlSession = sqlSessionFactory.openSession(true);
		//GuestMapper guestMapper=sqlSession.getMapper(GuestMapper.class);
		return guestMapper.delete(guestNo);
		//sqlSession.close();
	}

	@Override
	public Guest findByGuestNo(int guestNo) throws Exception {
		//SqlSession sqlSession = sqlSessionFactory.openSession(true);
		//GuestMapper guestMapper=sqlSession.getMapper(GuestMapper.class);
		return guestMapper.findByGuestNo(guestNo);
		//sqlSession.close();
	}

	@Override
	public List<Guest> findByAll() throws Exception {
		//SqlSession sqlSession = sqlSessionFactory.openSession(true);
		//GuestMapper guestMapper=sqlSession.getMapper(GuestMapper.class);
		return guestMapper.findByAll();
		//sqlSession.close();
	}

}
