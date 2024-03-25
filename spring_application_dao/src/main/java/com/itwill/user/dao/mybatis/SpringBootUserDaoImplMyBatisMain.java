package com.itwill.user.dao.mybatis;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.itwill.user.dao.mybatis.mapper.UserMapper;

@SpringBootApplication
public class SpringBootUserDaoImplMyBatisMain {
	public static void main(String[] args) throws Exception{
		System.out.println("----Spring Container초기화시작[ApplicationContext객체생성시작]");
		ApplicationContext applicationContext=SpringApplication.run(SpringBootUserDaoImplMyBatisMain.class, args);
		System.out.println("----Spring Container초기화끝[ApplicationContext객체생성끝]");
		DataSource dataSource=applicationContext.getBean(DataSource.class);
		System.out.println("1.DataSource:"+dataSource);
		System.out.println("User:"+applicationContext.getBean(User.class));
		//System.out.println("UserDao:"+applicationContext.getBean(UserDao.class));
		System.out.println("2.UserMapper:"+applicationContext.getBean(UserMapper.class));
		System.out.println("3.UserDao:"+applicationContext.getBean(UserDao.class));
		System.out.println();
	
		UserDao userDao=applicationContext.getBean(UserDao.class);
		System.out.println(userDao.findUserList());
		System.out.println(userDao.findUser("kanghada"));
		
		
	}

}
